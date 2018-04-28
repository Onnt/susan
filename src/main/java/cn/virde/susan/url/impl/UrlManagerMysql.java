package cn.virde.susan.url.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.virde.nymph.db.mysql.MySql;
import cn.virde.nymph.util.Log;
import cn.virde.susan.bean.Url;
import cn.virde.susan.setting.Option;
import cn.virde.susan.url.UrlManager;

/**
 * 
 * @author Virde
 * @date 2018年4月20日 下午3:43:58
 */
public class UrlManagerMysql implements UrlManager{
	
	private Option option ;
	private MySql<Url> mysql =  null ;
	
	public UrlManagerMysql(Option option) {
		this.option = option ;
		mysql = new MySql<Url>(option.getConnInfo());
	}

	@Override
	public boolean isExistUrl(String url) throws Exception{
		List<Url> list = mysql.query(sql("select * from tableName where url = ?"), new Object[] {url}, Url.class);
		return list != null && list.size() > 0 ;
	}

	@Override
	public int insert(String url) throws Exception {
		// url有效性判断，目前只是简单的验证非空
		if(StringUtils.isEmpty(url))
			return 0 ;		
		return mysql.executeSQL(
				sql(
						"INSERT INTO tableName(url) "
						+ "SELECT ? FROM DUAL "
						+ "WHERE NOT EXISTS(SELECT url FROM tableName WHERE url = ? )"), 
				new Object[]{url,url});
	}

	@Override
	public int insert(List<String> urls) throws Exception {
		int line = 0 ;
		for (String url : urls) {
			line += insert(url) ;
		}
		return line ;
	}

	@Override
	public int updateState(String url, int status) throws Exception {
		return mysql.executeSQL(sql("update tableName set state = ?,time = CURRENT_TIMESTAMP() where url = ? "),
				new Object[]{status,url});
	}

	@Override
	public int updateState(List<Url> list) throws Exception {
		int line = 0 ; 
		for(Url url : list) {
			line += updateState(url.getUrl(), url.getState());
		}
		return line ;
	}

	@Override
	public List<Url> getUrlsByStatusLimit(int status, int limit) throws Exception {
		return getUrlsByWhereAndLimit(" state = "+status ,  limit);
	}


	@Override
	public List<Url> getExtractUrl(int limit) throws Exception {
		List<Url> respList = mysql.query(sql("select * from tableName ORDER BY id limit ?"), new Object[] {limit}, Url.class);
		// 如果 返回对象（respList）为空
		replace(respList);
		return respList ;
	}
	public int replace(Url url) throws Exception {
		return mysql.executeSQL(sql("REPLACE INTO tableName (`url`, `state`) VALUES ( ?, ?)"), new Object[] {url.getUrl(),url.getState()});
	}
	public int replace(List<Url> list) throws Exception {
		int line = 0 ;
		for(Url url : list) {
			line += replace(url);
		}
		return line ;
	}
	
	@Override
	public List<Url> getAnalyUrl(int limit) throws Exception {
		List<Url> respList = getUrlsByStatusLimit(Url.STATE_NORMAL, limit);
		updateStateToAnaly(respList);
		return respList;
	}

	@Override
	public int updateStateToAnaly(Url url) throws Exception {
		url.setState(Url.STATE_ANALY);
		return updateState(url);
	}
	
	@Override
	public int updateStateToError(List<Url> list) throws Exception {
		int line = 0 ;
		for(Url url : list) {
			line += updateStateToError(url);
		}
		return line;
	}
	@Override
	public int updateStateToError(Url url) throws Exception {
		if(url.getState() < -100) {
			url.setState(url.getState() - 1);
		}
		if(0 < url.getState() && url.getState() < 100) {
			url.setState(-101);
		}
		if( 100 < url.getState()) {
			url.setState(url.getState() * -1 -1);
		}
//		if(url.getState() < -110) {
//			return deleteUrl(url.getUrl());
//		}else {
			return updateState(url);
//		}
	}

	@Override
	public int deleteUrl(String url) throws Exception {
		return mysql.executeSQL(sql("delete from tableName where url = ?"), new Object[] {url});
	}

	@Override
	public List<Url> getErrorUrls(int limit) throws Exception {
		return getUrlsByWhereAndLimit("state < -100", limit);
	}
	public List<Url> getUrlsByWhereAndLimit(String where ,int limit) throws Exception{
		return mysql.query(sql("select * from tableName where "+ where +" ORDER by id limit ?"), 
				new Object[] {limit},
				Url.class); 
	}

	@Override
	public int updateStateToAnaly(List<Url> list) throws Exception {
		int line = 0 ;
		for(Url url : list) {
			line += updateStateToAnaly(url);
		}
		return line;
	}



	@Override
	public int deleteUrl(List<Url> list) throws Exception {
		int line = 0 ;
		for(Url url : list) {
			line += deleteUrl(url.getUrl());
		}
		return line;
	}

	@Override
	public int updateState(Url url) throws Exception {
		return updateState(url.getUrl(), url.getState());
	}

	public void createTableIfNotExists() {
		try {
			mysql.executeSQL(sql(
						" CREATE TABLE  IF NOT EXISTS  `tableName` (	"+
						"   `id`  bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,	"+
						"   `url` varchar(800) COLLATE utf8_unicode_ci NOT NULL,	"+
						"   `state` int(11) DEFAULT '1',	"+
						"   `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,	"+
						"   UNIQUE KEY `url_index` (`url`) USING BTREE,	"+
						"   KEY `id_index` (`id`)	"+
						" ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;	"
					), 
					null);
		} catch (Exception e) {
			Log.error("数据库建表失败，程序退出", e);
			System.exit(0);
		}
	}

	public String sql(String sql) {
		if(sql==null || sql.trim().equals("")) return null;
		if(!sql.contains("tableName")) return sql;
		return sql.replace("tableName", option.getTableName());
	}
}

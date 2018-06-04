package cn.virde.susan.url;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.virde.nymph.db.mysql.MySql;
import cn.virde.nymph.util.Log;
import cn.virde.susan.bean.Url;
import cn.virde.susan.setting.Option;

public abstract class MysqlUrlManager extends UrlManager{
	
	protected Option option ;
	protected MySql<Url> mysql =  null ;
	
	public MysqlUrlManager(Option option) {
		this.option = option ;
		mysql = new MySql<Url>(option.getConnInfo());
	}

	public String sql(String sql) {
		if(sql==null || sql.trim().equals("")) return null;
		if(!sql.contains("tableName")) return sql;
		return sql.replace("tableName", option.getTableName());
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
	public int updateState(String url, int status) throws Exception {
		return mysql.executeSQL(sql("update tableName set state = ?,time = CURRENT_TIMESTAMP() where url = ? "),
				new Object[]{status,url});
	}

	@Override
	public int updateUrl(Url url) throws Exception {
		return updateState(url.getUrl(), url.getState());
	}

	public List<Url> getUrlsByWhereAndLimit(String where ,int limit) throws Exception{
		return mysql.query(sql("select * from tableName where "+ where +" ORDER by id limit ?"), 
				new Object[] {limit},
				Url.class); 
	}

	@Override
	public int deleteUrl(String url) throws Exception {
		return mysql.executeSQL(sql("delete from tableName where url = ?"), new Object[] {url});
	}
	@Override
	public List<Url> getUrlsByStatusLimit(int status, int limit) throws Exception {
		return getUrlsByWhereAndLimit(" state = "+status ,  limit);
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
	
}


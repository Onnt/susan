package cn.virde.susan.url.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.virde.susan.bean.Url;
import cn.virde.susan.setting.Option;
import cn.virde.susan.url.MysqlUrlManager;

/**
 * 
 * @author Virde
 * @date 2018年6月4日 下午2:48:19
 */
public class ShortMysqlUrlManager extends MysqlUrlManager{

	public static final int STATE_NORMAL = 0 ;
	public static final int STATE_EXTRACT = 1 ;
	public static final int STATE_ANALY = 2 ;
	
	public ShortMysqlUrlManager(Option option) {
		super(option);
	}

	/**
	 * 状态码小于0 的即为 错误链接
	 */
	@Override
	public List<Url> getErrorUrls(int limit) throws Exception {
		return getUrlsByWhereAndLimit("state < 0", limit);
	}

	/**
	 * 获取待爬取的链接，
	 * state == 0 即为待爬取链接。
	 * 如果没有不存在待爬取链接，则返回null
	 */
	@Override
	public List<Url> getExtractUrl(int limit) throws Exception {
		List<Url> respList = getUrlsByStatusLimit(STATE_NORMAL, limit);
		if(respList == null || respList.size() == 0) {
			return null ;
		}
		updateStateToExtract(respList);
		return respList;
	}

	@Override
	public List<Url> getAnalyUrl(int limit) throws Exception {
		List<Url> respList = getUrlsByStatusLimit(STATE_EXTRACT, limit);
		if(respList == null || respList.size() == 0) {
			return null ;
		}
		updateStateToAnaly(respList);
		return respList;
	}

	@Override
	public int updateStateToAnaly(Url url) throws Exception {
		return updateState(url.getUrl(),STATE_ANALY);
	}

	@Override
	public int updateStateToExtract(Url url) throws Exception {
		return updateState(url.getUrl(),STATE_EXTRACT);
	}
	
	@Override
	public int insert(String url) throws Exception {
		// url有效性判断，目前只是简单的验证非空
		if(StringUtils.isEmpty(url))
			return 0 ;		
		return mysql.executeSQL(
				sql(
						"INSERT INTO tableName(url,state) "
						+ "SELECT ?,0 FROM DUAL "
						+ "WHERE NOT EXISTS(SELECT url FROM tableName WHERE url = ? )"), 
				new Object[]{url,url});
	}
}

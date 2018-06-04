package cn.virde.susan.url.impl;

import java.util.List;

import cn.virde.susan.bean.Url;
import cn.virde.susan.setting.Option;
import cn.virde.susan.url.MysqlUrlManager;

/**
 * 链接状态详解：
 * 小于0的为出错的链接
 * 1 为待分析链接
 * 2 为已分析链接
 * 
 * 利用id的更新获取待爬取链接
 * @author Virde
 * @date 2018年4月20日 下午3:43:58
 */
public class SustainedMysqlUrlManager extends MysqlUrlManager{
	
	public SustainedMysqlUrlManager(Option option) {
		super(option);
	}

	@Override
	public List<Url> getExtractUrl(int limit) throws Exception {
		List<Url> respList = mysql.query(sql("select * from tableName ORDER BY id limit ?"), new Object[] {limit}, Url.class);
		// 如果 返回对象（respList）为空
		updateStateToExtract(respList);
		return respList ;
	}
	
	@Override
	public int updateStateToExtract(Url url) throws Exception {
		return mysql.executeSQL(sql("REPLACE INTO tableName (`url`, `state`) VALUES ( ?, ?)"), new Object[] {url.getUrl(),url.getState()});
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
		return updateUrl(url);
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
	public List<Url> getErrorUrls(int limit) throws Exception {
		return getUrlsByWhereAndLimit("state < -100", limit);
	}
	@Override
	public int updateStateToAnaly(List<Url> list) throws Exception {
		int line = 0 ;
		for(Url url : list) {
			line += updateStateToAnaly(url);
		}
		return line;
	}



}

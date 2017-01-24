package cn.blacard.susan;

import cn.blacard.dbopera.query.QueryOften;
import cn.blacard.susan.dao.UrlsEntity;

public class Susan {
	public static QueryOften<UrlsEntity> query = new QueryOften<UrlsEntity>(Setting.getDBConnectPara());
	
}
 
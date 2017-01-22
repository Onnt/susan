package cn.blacard.gosong;

import cn.blacard.dbopera.constant.DBStyle;
import cn.blacard.dbopera.opera.QueryObject;
import cn.blacard.dbopera.para.DBConnectPara;
import cn.blacard.susan.dao.UrlsDao;
import cn.blacard.susan.dao.UrlsEntity;
import cn.blacard.susan.page.Page;
import cn.blacard.susan.thread.Engine;

public class GodSong {
	
	private static QueryObject<UrlsEntity> query = new QueryObject<UrlsEntity>(new DBConnectPara(
			DBStyle.MYSQL,
			"127.0.0.1",
			"spider",
			"root",
			"yunbin"
			));
	
	public static void main(String[] args) {
		Engine engine = new Engine("http://shenqu.yy.com");
		engine.start();
		
//		getResult();
	}
//	
//	public static void main(String[] args) {
//		Page page = new Page("http://shenqu.yy.com/play/id_1081696283498561416.html");
//		String html = page.getHtml();
//		String deal =html.substring(html.indexOf("worksUrl:\"")+10, html.indexOf("\",worksName"));
//		System.out.println(deal);
//	}
	
	public static void getResult(){
		Page page = new Page(UrlsDao.getAnalyUrl());
		
		if(page.getPageUrl().contains("/play/id_")){
			String html = page.getHtml();
			String deal =html.substring(html.indexOf("worksUrl:\"")+10, html.indexOf("\",worksName"));
			query.executeSQL("insert into godsong(song) value(?);", new Object[]{deal});
			System.out.println(deal);
		}
		getResult();
	}
}

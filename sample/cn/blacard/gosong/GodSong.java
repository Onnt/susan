package cn.blacard.gosong;

import cn.blacard.susan.dao.UrlsDao;
import cn.blacard.susan.page.Page;
import cn.blacard.susan.thread.Engine;

public class GodSong {
	
	
	public static void main(String[] args) {
		Engine engine = new Engine("http://shenqu.yy.com/");
		engine.start();
		

		Engine engine2 = new Engine("http://www.ppmsg.com/");
		engine2.start();
		
		
		getResult();
	}

	
	public static void getResult(){
		
		Page page = new Page(UrlsDao.getAnalyUrl());
		
		if(page.getPageUrl().contains("/play/id_")){
			String html = page.getHtml();
			String deal =html.substring(html.indexOf("worksUrl:\"")+10, html.indexOf("\",worksName"));
			UrlsDao.query.executeSQL("insert into godsong(song) value(?);", new Object[]{deal});
			System.out.println(deal);
		}
		getResult();
	}
}

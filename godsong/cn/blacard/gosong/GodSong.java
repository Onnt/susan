package cn.blacard.gosong;

import cn.blacard.susan.dao.UrlsDao;
import cn.blacard.susan.page.Page;
import cn.blacard.susan.thread.Engine;

public class GodSong {
	public static void main(String[] args) {
		Engine engine = new Engine("http://shenqu.yy.com");
		engine.start();
		
		getResult();
	}
	
	public static void getResult(){
		Page page = new Page(UrlsDao.getAnalyUrl());
		String html = page.getHtml();
		String deal =html.substring(html.indexOf("video: \""), html.indexOf("subVideoArray:[],"));
		System.out.println(deal);
		getResult();
	}
}

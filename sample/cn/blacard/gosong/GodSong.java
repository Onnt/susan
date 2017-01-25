package cn.blacard.gosong;

import java.util.Date;

import cn.blacard.nymph.date.NymTime;
import cn.blacard.nymph.net.down.DownFromUrl;
import cn.blacard.nymph.random.NumberRandom;
import cn.blacard.susan.Susan;
import cn.blacard.susan.dao.UrlsDao;
import cn.blacard.susan.page.Page;
import cn.blacard.susan.thread.Engine;

public class GodSong {
	
	
	public static void main(String[] args) {
		Engine engine = new Engine("http://shenqu.yy.com/");
		engine.start();
		
		down();
		getResult();
	}

	
	public static void getResult(){
		
		Page page = new Page(UrlsDao.getAnalyUrl());
		
		if(page.getPageUrl().contains("/play/id_")){
			String html = page.getHtml();
			String deal =html.substring(html.indexOf("worksUrl:\"")+10, html.indexOf("\",worksName"));
			Susan.query.executeSQL("insert into godsong(song) value(?);", new Object[]{deal});
			System.out.println(deal);
		}
		getResult();
	}
	
	
	private static DownFromUrl down = new DownFromUrl();
	public static void down(){
		try{
			String fileName = NymTime.toString(new Date(), "YYYYMMDD_HH-mm-ss")+NumberRandom.getRandom(0, 1000000)+".mp4";
			down.downFromUrl(GodsongDao.getDownUrl(), fileName, "D://godsong/");
		}catch(Exception e){
			
		}
		down();
	}
	
}

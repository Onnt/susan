package cn.virde.law;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.virde.nymph.Nym;
import cn.virde.nymph.db.exception.NymDBException;
import cn.virde.susan.Susan;
import cn.virde.susan.dao.UrlsDao;
import cn.virde.susan.thread.Engine;

public class Pic {
	public static void main(String[] args) throws IOException, NymDBException, SQLException {
		Susan.setTableName("spider_lofter_virde");
		Susan.setConnInfo("virde.cn", "db_chun", "blacard", "HaHaHaHa");
		
		spider();
		
		getDown();
		
//		test();
		
	}
	private static void spider() {
		
		Engine engine = new Engine("http://virde.lofter.com");
		engine.start();
	}
	
	private static void getDown() throws NymDBException, SQLException, IOException {
		String analyUrl = UrlsDao.getAnalyUrl("select * from "+Susan.tableName+" where analy is null and url like 'http://virde.lofter.com/post/%' limit 1;");
		while(analyUrl != null) {
			analyUrl = UrlsDao.getAnalyUrl("select * from "+Susan.tableName+" where analy is null and url like 'http://virde.lofter.com/post/%' limit 1;");
			Document doc = Jsoup.connect(analyUrl).get();
			Elements eles = doc.select("div.img > a");
			for(Element ele : eles) {
				String picUrl = ele.attr("bigimgsrc");
				if(picUrl!=null)
					Nym.down.downFromUrl(picUrl, new Date().getTime()+".jpg", "D://Lofter");
			}
		}
	}
	
	private static void test() throws IOException, NymDBException, SQLException {
//		Page page = new PageByJsoup(" http://janmi-lee.lofter.com/post/1cba5d0e_1173cd4e");
//		System.out.println(page.getHtml());
		
//		String analyUrl = UrlsDao.getAnalyUrl("select * from urls where url like 'http://janmi-lee.lofter.com/post/%' limit 1;");
//		System.out.println(analyUrl);
		
		System.out.println(Nym.string.getFileName("http://imglf6.nosdn.127.net/img/a0RnMDJkcHdKSDNQaHJURVNiQWJUTSs2UGlhakRmc0dGSGRpbUF3cEdYSDdzeXZBWTgzUUxnPT0.jpg?imageView&thumbnail=1680x0&quality=96&stripmeta=0&type=jpg"));
	}
	
}

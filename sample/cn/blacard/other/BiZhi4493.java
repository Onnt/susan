package cn.blacard.other;

import java.io.IOException;
import java.util.Set;

import cn.blacard.nymph.String.StringTool;
import cn.blacard.nymph.net.down.DownFromUrl;
import cn.blacard.susan.dao.UrlsDao;
import cn.blacard.susan.page.Page;
import cn.blacard.susan.page.PageDeal;
import cn.blacard.susan.thread.Engine;

public class BiZhi4493 {
	

	private static DownFromUrl down = new DownFromUrl();
	
	
	public static void main(String[] args) throws IOException {
//		Engine engine = new Engine("https://ypy.douban.com/");
//		engine.start();
		download();
	}
	
	
	/**
	 * @throws IOException 
	 * 
	 */
	public static void download() throws IOException{
		
		String extractUrl = UrlsDao.getExtractUrl();
		
		Set<String> sets = PageDeal.getSrc(new Page(extractUrl));
		
		for(String str : sets){
			try{
				//down.downFromUrl(str,str+".jpg","D://hehe");
		    	down.downFromUrl(str, StringTool.getFileName(str+".jpg"), "D://NymDownLoad");
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("出现一个未知错误");
			}
		}
		download();
	}
	
}


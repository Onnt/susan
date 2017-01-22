package cn.blacard.susan.thread;

import java.util.Set;

import cn.blacard.susan.dao.UrlsDao;
import cn.blacard.susan.page.Page;

public class Engine extends Thread{
	
	public Engine(String url) {
		UrlsDao.insert(url);
	}
	
	@Override
	public void run() {
		while(true){
			cycle();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void cycle(){
		String url = UrlsDao.getExtractUrl();
		if(url!=null){
			try{
				Page page = new Page(url);
				Set<String> urls = page.getUrls();
				for(String s : urls){
					UrlsDao.insert(s);
				}
			}catch(Exception e){
				System.out.println("url："+url+" 此链接出现异常");
				UrlsDao.markError(url, "error");
			}
		}
	}
}

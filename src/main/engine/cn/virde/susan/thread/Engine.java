package cn.virde.susan.thread;

import java.util.Set;

import cn.virde.nymph.net.page.PageDeal;
import cn.virde.nymph.util.Log;
import cn.virde.susan.dao.UrlsDao;
import cn.virde.susan.page.Page;
import cn.virde.susan.page.PageByJsoup;

public class Engine extends Thread{
	
	private String host ;
	private int d = 0 ;
	public Engine(String url) {
		UrlsDao.insert(url);
		this.host = PageDeal.getHostName(url);
	}
	
	@Override
	public void run() {
		while(true){
			
			if(d == 10) {
				Log.info("连续十分钟没有新的待爬取链接。线程结束");
				return ;
			}
			
			int sleep = cycle();
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public int cycle(){
		
		String url = UrlsDao.getExtractUrl();
		
		if(url==null) {
			Log.info("Engine：获取到了空的链接，线程将在一分钟后再次获取待提取链接");
			d++ ;
			return 1000 * 60 ;
		} 
		
		d = 0 ;
				
		// 只在这个网站中爬取
		if(!url.startsWith(host)) return 0;
		
		Log.info("正在爬取：" + url);
		try{
			Page page = new PageByJsoup(url); 
			Set<String> urls = page.getHrefs();
			for(String s : urls){
				UrlsDao.insert(s);
			}
		}catch(Exception e){
			System.out.println("url："+url+" 此链接出现异常");
			UrlsDao.markError(url);
		}
		return 0 ;
	}
}

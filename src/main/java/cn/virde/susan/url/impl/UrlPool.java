/**
 * 
 */
package cn.virde.susan.url.impl;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

import cn.virde.nymph.util.Log;
import cn.virde.susan.bean.Url;
import cn.virde.susan.monitor.Monitor;
import cn.virde.susan.setting.Option;

/**
 * @author Virde
 * @date 2018年6月20日 下午6:41:20
 */
public class UrlPool {

	private Option option  ;
	ConcurrentLinkedDeque<Url> urlPool = new ConcurrentLinkedDeque<Url>();
	
	public  UrlPool(Option option) {
		this.option = option ;
		initUrlPool();
	}
	
	public boolean initUrlPool() {
		List<Url> respList = getExtractUrl(option.getLineNumber() * 10) ;
		if(respList != null && respList.size() > 0) {
			urlPool.addAll(respList);
			return true ;
		}else {
			return false ;
		}
	}
	
	public Url getFirstUrl() {
		// 这个系数可以考虑改成自动化的
		if(urlPool.size() < option.getLineNumber() * 9) {
			Log.debug("连接池里面的链接跟不上了…………");
			new Thread(()->{
				urlPool.addAll(getExtractUrl(option.getLineNumber() * 5 ));				
			}).start();
		}else {
			Log.debug("连接池链接跟的上……");
		}
		if(urlPool.size() > 0 ) {
			return urlPool.removeFirst() ;			
		}else {
			return null ;
		}
	}
	/**
	 * 
	 * @author Virde
	 * @date 2018年6月5日 下午1:15:03
	 * @return
	 */
	private List<Url> getExtractUrl(int number){
		try {
			long start = System.currentTimeMillis() ;
			List<Url> list = option.um.getExtractUrl(number);
			long end = System.currentTimeMillis() ;
			Monitor.recordExtractSpendTime(start, end);
			return list ;
		} catch (Exception e) {
			Log.error("控制器在查询待爬取链接时数据库可能出现异常，将在10秒后重新尝试查询", e);
			try {Thread.sleep(10_000);} catch (InterruptedException e1) {e1.printStackTrace();}
			return getExtractUrl() ;
		}
	}

	private List<Url> getExtractUrl(){
		return getExtractUrl(option.getLineNumber());
	}
}

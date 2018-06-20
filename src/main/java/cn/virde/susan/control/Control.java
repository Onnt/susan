/**
 * 
 */
package cn.virde.susan.control;

import cn.virde.nymph.util.Log;
import cn.virde.susan.bean.Url;
import cn.virde.susan.setting.Option;
import cn.virde.susan.url.impl.UrlPool;

/**
 * @author Virde
 * @date 2018年6月20日 上午11:32:41
 */
public class Control {

	private Option option ;
	private UrlPool urlPool ;
	private UrlDealThreadPool threadPool ;
	
	public Control(Option option) {
		this.option = option ;
		urlPool = new UrlPool(option);
		threadPool = new UrlDealThreadPool(option.getLineNumber());
//		Log.alert("Controller 创建成功");
	}
	
	public void start() {
		option.event.startFunc.done();
		getAndDealHostUrlFirst();
		while(true) {
			sleepByOption() ;
			if(threadPool.canAddThread()) {
//				Log.alert(" 正在添加新的UrlDeal线程 ");
				Url url = getDealUrl() ;
				if(url == null ) {
					break;
				}
				threadPool.execute(new UrlDealThread(option,url));
			}else {
//				Log.alert(" 不能添加新的线程，将休息1秒钟 ");
				sleep(1000);
			}
		}
		option.event.endFunc.done();
	}

	private void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void sleepByOption() {
		if(option.getSleepTime() != 0 ) {
			sleep(option.getSleepTime());
		}
	}
	public Url getDealUrl() {
		Url url = urlPool.getFirstUrl() ;
		if(url == null ) {
			urlPool.initUrlPool() ;
			url = urlPool.getFirstUrl() ;
			Log.alert("链接池获取链接为空，正在重新初始化连接池并重新获取，待dealUrl ");
			if(url == null) {
				Log.alert("第二次从链接池获取链接，但仍然失败。将返回null，系统可能会停止运行 ");				
				return null ;
			}
		}	

//		Log.alert(" 成功获取链接 ");
		return url ;	
	}

	private void getAndDealHostUrlFirst() {
		if(option.getHost()!=null) {
			Url url = new Url() ;
			url.setUrl(option.getHost());
			Thread hostUrlDealThread = new UrlDealThread(option,url);
			hostUrlDealThread.start();
			try {
				hostUrlDealThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}

//		Log.alert("  start之前首先处理host域名，");
	}
}

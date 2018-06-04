package cn.virde.susan.control;

import java.util.List;

import cn.virde.nymph.util.Log;
import cn.virde.susan.bean.Url;
import cn.virde.susan.setting.Option;
import cn.virde.susan.url.UrlManager;

/**
 * 
 * @author Virde
 * @date 2018年4月23日 下午2:04:10
 */
public class Control {
	
	private Option option ;
	private UrlManager um;
	public Control(Option option) {
		this.option = option ;
		um = option.getUm() ;
	}
	
	public void start() {
		// 控制器开始之前，用户自己需要做一下什么事情
		option.event.startFunc.done();
		
		if(option.getHost()!=null) {
			Url url = new Url() ;
			url.setUrl(option.getHost());
			Thread hostUrlDealThread = new UrlDeal(option,url);
			hostUrlDealThread.start();
			try {
				hostUrlDealThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
		// 这个地方是有问题的，这里的线程创建速度其实是和数据库的查询性能挂钩的，数据库出数据的速度有多快，线程的创建速度就有多快
		while(true) {
//			long start = System.currentTimeMillis() ;
			boolean isContinue = cycle();
			if(!isContinue) {
				option.event.endFunc.done();
				System.out.println("program is end");
				break ;
			}
//			long end = System.currentTimeMillis() ;
//			Log.alert("此次循环消耗时间："+(end - start)+ "ms");
		}
	}
	
	/**
	 * 不间断的执行爬取任务
	 * @author Virde
	 * @date 2018年6月4日 下午4:07:58
	 * @return 返回false，停止循环
	 */
	public boolean cycle() {
		try {
			List<Url> list = um.getExtractUrl(option.getLineNumber());
			
			if(list == null ) {
				return false ;
			}
			for(Url url : list) {
				new UrlDeal(option,url).start();
			}

			if(option.getSleepTime() != 0 ) {
				Thread.sleep(option.getSleepTime());
			}
		} catch (Exception e) {
			Log.error("遇到严重错误，系统退出", e);
		}
		return true ;
	}
}

/**
 * 
 */
package cn.virde.susan.control;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 
 * @author Virde
 * @date 2018年6月20日 上午10:52:43
 */
public class UrlDealThreadPool{
	ThreadPoolExecutor pool ;
	public UrlDealThreadPool(int size) {
		pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(size);
	}
	public boolean canAddThread() {
//		Log.alert("getActiveCount："+pool.getActiveCount() +" corePoolSize :"+pool.getCorePoolSize());
		return pool.getActiveCount() < pool.getCorePoolSize() ;
	}
	public void execute(Thread thread) {
		pool.execute(thread);
	}
}
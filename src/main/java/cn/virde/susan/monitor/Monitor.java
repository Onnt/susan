package cn.virde.susan.monitor;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.virde.nymph.Nym;
import cn.virde.nymph.text.TextOut;
import cn.virde.susan.bean.Url;

/**
 * 记录程序运行时的出错信息。
 * 记录程序的运行效率（某些重要节点运行时长）
 * @author Virde
 * @date 2018年4月23日 下午2:54:05
 */
public class Monitor{
	static {
		try {
			Nym.file.newFile("D://var//lofter/monitor_log.md");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static TextOut to = new TextOut("D://var//lofter/monitor_log.md");
	
	// 记录获取待爬取链接的消耗时长
	private static volatile List<Integer> extractSpendTimes = new LinkedList<Integer>();
	// 记录单个链接的处理时长
	private static volatile List<Integer> urlDealSpendTimes = new LinkedList<Integer>();
	// 错误链接的数量
	
	public static void recordExtractSpendTime(long start , long end) {
		extractSpendTimes.add((int) (end - start)) ;
	}
	public static void recordUrlDealSpendTime(Url url,long start,long end) {
		urlDealSpendTimes.add((int) (end - start)) ;
	}
	
	//每分钟将监控数据存储到本地文件
	public static void output() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("* " + Nym.time.toString(new Date()) +"\n");
		sb.append(getExtractSpendTimesText());
		sb.append(getUrlDealSpendTimesText());
		
		extractSpendTimes.clear();
		urlDealSpendTimes.clear();
		
		to.putln(sb.toString());
	}

	private static String getExtractSpendTimesText() {
		int size = extractSpendTimes.size() ;
		if(size == 0) return  "获取待爬取链接平均消耗时长：0 (0) \n" ;
		
		int total = 0 ;
		for(int i : extractSpendTimes) {
			total += i ;
		}
		int avg = total / size ;
		return  "获取待爬取链接平均消耗时长："+avg+" (" + size + ") \n" ;
	}

	private static String getUrlDealSpendTimesText() {
		int size = urlDealSpendTimes.size() ;
		if(size == 0) return  "单个链接平均处理时长：0 (0) \n" ;
		
		int total = 0 ;
		for(int i : urlDealSpendTimes) {
			total += i ;
		}
		int avg = total / size ;
		return  "单个链接平均处理时长："+avg+" (" + size + ") \n" ;
	}
	
	public static void start() {
		Timer timer = new Timer();  
        timer.schedule(new TimerTask() {  
            public void run() {  
                  output();
                  start();
            }  
        }, 60_000);// 设定指定的时间time,此处为2000毫秒  
	}
}
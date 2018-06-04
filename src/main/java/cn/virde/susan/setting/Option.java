package cn.virde.susan.setting;

import cn.virde.nymph.common.info.ValidInfo;
import cn.virde.nymph.db.ConnInfo;
import cn.virde.susan.url.UrlManager;
import cn.virde.susan.url.impl.ShortMysqlUrlManager;

/**
 * 
 * @author Virde
 * @date 2018年4月24日 上午10:36:39
 */
public class Option {
	
	// 爬取线程数量
	private int lineNumber = 5 ;
	// 程序休息时间，当设置休息时间时，lineNumber强制为1
	private int sleepTime = 0 ;
	
	// 默认存储的数据库名称
	private String tableName = "susan_spider";
	// 数据库连接信息
	private ConnInfo connInfo;
	
	// 是否使用代理，以及代理详细信息
	private boolean isUseProxy = false ;
	private String proxy_host ; 
	private int proxy_port ;

	// 需要爬取的网站主域名和爬虫爬取的范围
	private String host ;
	private String rangeHost ;
	
	// 爬取过程中的事件动作
	public Event event ;
	
	// 设置爬虫的Url链接管理功能以适应不同的需求
	public UrlManager um;
	
	// 设置是否为开发者模式，主要用来控制日志输出
	private boolean isDev ;
	
	public Option() {
		event = new Event();
		event.onStart(()->{;});
		event.onEnd(()->{;});
		
	}
	/**
	 * 验证各项参数是否正确
	 * @author Virde
	 * @date 2018年4月24日 上午10:36:43
	 * @return
	 */
	public ValidInfo valid() {
		ValidInfo info = new ValidInfo();
		if(host == null) {
			info.add("Host 未指定，将导致程序启动失败");
		}
		if(rangeHost == null ) {
			info.add("rangeHost未指定，将导致程序启动失败");
		}
		return info;
	}
	
	public String getRangeHost() {
		return rangeHost;
	}

	public Option setRangeHost(String rangeHost) {
		this.rangeHost = rangeHost;
		return this ;
	}

	public String getHost() {
		return host;
	}

	public Option setHost(String host) {
		this.host = host;
		return this ;
	}
	public String getTableName() {
		return tableName;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}

	public Option setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
		return this ;
	}

	public Option setTableName(String tableName) {
		this.tableName = tableName;
		return this ;
	}
	

	public String getProxy_host() {
		return proxy_host;
	}

	public Option setProxy_host(String proxy_host) {
		this.proxy_host = proxy_host;
		return this ;
	}

	public int getProxy_port() {
		return proxy_port;
	}

	public Option setProxy_port(int proxy_port) {
		this.proxy_port = proxy_port;
		return this ;
	}

	public ConnInfo getConnInfo() {
		return connInfo;
	}
	
	public Option setConnInfo(ConnInfo connInfo) {
		this.connInfo = connInfo;
		return this ;
	}
	public Option setConnInfo(String ip,String dbName,String user,String pass) {
		this.connInfo = new ConnInfo().setIp(ip).setDbName(dbName).setUser(user).setPass(pass);
		return this ;
	}

	public boolean isUseProxy() {
		return isUseProxy;
	}

	public Option setUseProxy(boolean isUseProxy) {
		this.isUseProxy = isUseProxy;
		return this ;
	}

	public int getSleepTime() {
		return sleepTime;
	}

	public Option setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
		this.lineNumber = 1 ;
		return this ;
	}
	public UrlManager getUm() {
		if(um == null )
			um = new ShortMysqlUrlManager(this);
		return um ;
	}
	public void setUm(UrlManager um) {
		this.um = um;
	}
	public boolean isDev() {
		return isDev;
	}
	public void setDev(boolean isDev) {
		this.isDev = isDev;
	}
	
}

package cn.virde.susan.setting;

import cn.virde.nymph.common.info.ValidInfo;
import cn.virde.nymph.db.ConnInfo;
import cn.virde.nymph.util.Log;

/**
 * 
 * @author Virde
 * @date 2018年4月24日 上午10:36:39
 */
public class Option {
	
	private int lineNumber = 5 ;
	// 程序休息时间，当设置休息时间时，lineNumber强制为1
	private int sleepTime = 0 ;
	
	private String tableName = "urls";
	private ConnInfo connInfo;
	
	private boolean isUseProxy = false ;
	private String proxy_host ; 
	private int proxy_port ;

	private String host ;
	private String rangeHost ;
	
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
	
}

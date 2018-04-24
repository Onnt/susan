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

	public void setRangeHost(String rangeHost) {
		this.rangeHost = rangeHost;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	public String getTableName() {
		return tableName;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	

	public String getProxy_host() {
		return proxy_host;
	}

	public void setProxy_host(String proxy_host) {
		this.proxy_host = proxy_host;
	}

	public int getProxy_port() {
		return proxy_port;
	}

	public void setProxy_port(int proxy_port) {
		this.proxy_port = proxy_port;
	}

	public ConnInfo getConnInfo() {
		return connInfo;
	}
	
	public void setConnInfo(ConnInfo connInfo) {
		this.connInfo = connInfo;
	}
	public void setConnInfo(String ip,String dbName,String user,String pass) {
		this.connInfo = new ConnInfo().setIp(ip).setDbName(dbName).setUser(user).setPass(pass);
	}

	public boolean isUseProxy() {
		return isUseProxy;
	}

	public void setUseProxy(boolean isUseProxy) {
		this.isUseProxy = isUseProxy;
	}
	
}

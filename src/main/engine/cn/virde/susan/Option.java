package cn.virde.susan;

import cn.virde.nymph.db.ConnInfo;
public class Option {
	
	private String tableName = "urls";
	private ConnInfo connInfo;
	
	private boolean isUseProxy = false ;
	private String proxy_host ; 
	private int proxy_port ;
	
	public String getTableName() {
		return tableName;
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

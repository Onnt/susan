package cn.virde.susan;

import cn.virde.nymph.db.ConnInfo;
public class Option {
	
	private String tableName = "urls";
	private ConnInfo connInfo;
	
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
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
	
	
}

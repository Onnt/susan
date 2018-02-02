package cn.virde.susan;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

import cn.virde.nymph.db.ConnInfo;
import cn.virde.susan.thread.Engine;

public class Susan {
	
	private final static Option option = new Option();
	
	public static ConnInfo getConnInfo() {
		return option.getConnInfo();
	}
	public static void setConnInfo(ConnInfo connInfo) {
		option.setConnInfo(connInfo);
	}
	public static void setConnInfo(String ip,String dbName,String user,String pass) {
		option.setConnInfo(ip, dbName, user, pass);
	}
	public static void setTableName(String tableName) {
		option.setTableName(tableName);
	}
	public static String getTbName() {
		return option.getTableName();
	}
	public static void setProxy(String host, int port) {
		option.setUseProxy(true);
		option.setProxy_host(host);
		option.setProxy_port(port);
	}
	
	public static Option getOption() {
		return option;
	}
	public final static String sql(String sql) {
		if(sql==null || sql.trim().equals("")) return null;
		if(!sql.contains("tableName")) return sql;
		return sql.replace("tableName", option.getTableName());
	}
	
	public static void startEngine(String url) {
    	Engine engine = new Engine(url);
    	engine.start();
	}
	
	public final static WebClient client = new WebClient(BrowserVersion.CHROME);
	static {
		//开启cookie管理
        client.getCookieManager().setCookiesEnabled(true);
        //开启js解析。对于变态网页，这个是必须的
        client.getOptions().setJavaScriptEnabled(true);
        //开启css解析。对于变态网页，这个是必须的。
        client.getOptions().setCssEnabled(true);
        //关闭报错抛出
        client.getOptions().setThrowExceptionOnFailingStatusCode(false);
        client.getOptions().setThrowExceptionOnScriptError(false);
        //设置超时时长
        client.getOptions().setTimeout(10000);
	}
}

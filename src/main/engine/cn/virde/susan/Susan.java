package cn.virde.susan;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

import cn.virde.nymph.db.ConnInfo;
import cn.virde.susan.thread.Engine;

public class Susan {
	private static ConnInfo connInfo ;
	
	public static ConnInfo getConnInfo() {
		return connInfo;
	}
	public static void setConnInfo(ConnInfo connInfo) {
		Susan.connInfo = connInfo;
	}
	public static void setConnInfo(String ip,String dbName,String user,String pass) {
		Susan.connInfo = new ConnInfo().setIp(ip).setDbName(dbName).setUser(user).setPass(pass);
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

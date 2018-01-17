package cn.virde.law;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

import cn.virde.nymph.Nym;
import cn.virde.nymph.exception.Not200Exception;
import cn.virde.nymph.util.Log;

public class BlaAcctact {

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
	
	public static void main(String[] args) throws IOException, Not200Exception {
		Page page = client.getPage("http://blacard.cn/?/account/login/");
//		page.
	}
	
	
//	for(int i = 0 ; i < 300 ; i ++) {
//		Log.alert("第 "+ i + "次执行成功…");
//		Map<String,String> params = new HashMap<String,String>();
//		params.put("post_hash", "3ce431a692ae1e9112f0b73a69da1ff7");
//		params.put("attach_access_key", "9bbf1dd97c50d299ecf6dc2b1b7b58c3");
//		params.put("category_id", "6");
//		params.put("question_content", "罗鑫 才是真正的 大大大大大大傻逼！"+new Date().getTime());
//		params.put("topics", "儿童");
//		
//		Nym.http.post("http://blacard.cn/?/publish/ajax/publish_question/", params);
//	}
}

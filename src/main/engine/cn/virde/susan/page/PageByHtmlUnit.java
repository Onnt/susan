package cn.virde.susan.page;

import java.io.IOException;
import java.net.URL;
import java.util.Set;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import cn.virde.susan.Susan;

public class PageByHtmlUnit extends Page{

	public PageByHtmlUnit(String url) {
		super(url);
	}

	public static void main(String[] args) {

	}

	@Override
	public Set<String> getUrls() {
		return null;
	}

	@Override
	public Set<String> getHrefs() {
		return null;
	}

	@Override
	public Set<String> getSrcs() {
		return null;
	}

	private HtmlPage getHtmlPage() throws FailingHttpStatusCodeException, IOException {
		   URL link=new URL(this.pageUrl); 
	        WebRequest request=new WebRequest(link); 
	        ////设置请求报文头里的User-Agent字段
	        request.setAdditionalHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
	        request.setAdditionalHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4");

	        try {
	        	HtmlPage page = Susan.client.getPage(request);	
	        	return page;
	        	
			}catch(NumberFormatException e) {
				System.out.println("不要输出");
			}catch (Exception e) {
				System.out.println("错误");
			}
	        return null;
	}

	@Override
	public String getHtml() {
		try {
			this.html = getHtmlPage().asXml();
			return html;
		}catch(Exception e) {
			System.out.println("获取页面内容时出现异常，页面地址：" + this.pageUrl);
			return "";
		}
	}
	
}

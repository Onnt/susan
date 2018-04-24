package cn.virde.susan.page.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.virde.susan.page.Page;

/**
 * 
 * @author Virde
 * @time 2017年11月1日 下午5:16:41
 */
public class PageByJsoup extends Page{

	private Document doc ;
	
	public PageByJsoup(String url) throws IOException {
		super(url);
//		if(Susan.getOption().isUseProxy()) {
//			this.doc = Jsoup.connect(this.pageUrl).proxy(Susan.getOption().getProxy_host(), Susan.getOption().getProxy_port()).get();
//		}else {
			this.doc = Jsoup.connect(this.pageUrl).get();
//		}
	}

	@Override
	public Set<String> getUrls() {
		
		return null;
	}

	/**
	 * 这个方法可能会抛空指针异常
	 */
	@Override
	public Set<String> getHrefs(){
		Set<String> hrefs = new HashSet<String>();
        
		Elements eles = doc.select("a[href]");

		for(Element ele : eles) {
			String href = ele.attr("abs:href");
			// 发现有些网站#后面的字段是有作用的。所以需要具体思考下这个需不需要截取
//			if(href.contains("#")) {
//				href = href.substring(0, href.indexOf("#"));
//			}
			hrefs.add(href);
		}
		return hrefs;
	}

	@Override
	public Set<String> getSrcs() {
		return null;
	}

	@Override
	public String getHtml() {
		return doc.html();
	}

}

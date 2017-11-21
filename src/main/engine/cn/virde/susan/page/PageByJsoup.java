package cn.virde.susan.page;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author Virde
 * @time 2017年11月1日 下午5:16:41
 */
public class PageByJsoup extends Page{

	private Document doc ;
	
	public PageByJsoup(String url) throws IOException {
		super(url);
		this.doc = Jsoup.connect(this.pageUrl).get();
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
			hrefs.add(ele.attr("abs:href"));
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

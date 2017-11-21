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

	public PageByJsoup(String url) {
		super(url);
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
        Document doc = getDoc();
		Elements eles = doc.select("a[href]");

		for(Element ele : eles) {
			hrefs.add(ele.attr("abs:href"));
		}
		return hrefs;
	}
	
	private Document getDoc() {
		try {
			return Jsoup.connect(this.pageUrl).get();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Set<String> getSrcs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHtml() {
		// TODO Auto-generated method stub
		return null;
	}

}

package cn.virde.susan.page;

import java.util.Set;

public abstract class Page {
	
	// 页面html代码
	protected String html ;
	// 页面的链接
	protected String pageUrl;
	// 页面的域名
	protected String host ;
	
	public Page(String url) {
		this.pageUrl = url ;
	}
	
	/**
	 * 获取页面中包含的所有绝对链接
	 * 包含网页链接和资源链接
	 * @return
	 */
	public abstract Set<String> getUrls();
	
	/**
	 * 获取页面中所有href绝对链接
	 * 一般是网页链接
	 * @return
	 */
	public abstract Set<String> getHrefs();
	
	/**
	 * 获取页面中所有Src链接
	 * 一般是资源链接
	 * @return
	 */
	public abstract Set<String> getSrcs();
	
	public abstract String getHtml();
}

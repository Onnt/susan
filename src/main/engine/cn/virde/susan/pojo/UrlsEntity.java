package cn.virde.susan.pojo;

import java.io.Serializable;

public class UrlsEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6856420858677424803L;
	
	private String url;
	
	private String extract;
	
	private String analy;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExtract() {
		return extract;
	}

	public void setExtract(String extract) {
		this.extract = extract;
	}

	public String getAnaly() {
		return analy;
	}

	public void setAnaly(String analy) {
		this.analy = analy;
	}

	public UrlsEntity(String url, String extract, String analy) {
		super();
		this.url = url;
		this.extract = extract;
		this.analy = analy;
	}

	public UrlsEntity() {
		super();
	}
	
	
	
}

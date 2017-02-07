package cn.blacard.gosong;

import cn.blacard.susan.page.Page;

public class Test {
	public static void main(String[] args) {
		Page page = new Page("https://coding.net/");
		System.out.println(page.getHtml());
		for(String s : page.getUrls()){
			System.out.println(s);
		}
	}
}

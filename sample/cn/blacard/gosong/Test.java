package cn.blacard.gosong;

import cn.blacard.susan.page.Page;

public class Test {
	public static void main(String[] args) {
		Page page = new Page("http://baidu.com");
		System.out.println(page.getHtml());
		
		for(String str : page.getUrls()){
			System.out.println(str);
		}
	}
}

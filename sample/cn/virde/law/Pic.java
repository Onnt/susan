package cn.virde.law;

import java.io.IOException;

import cn.virde.susan.Susan;
import cn.virde.susan.page.Page;
import cn.virde.susan.page.PageByHtmlUnit;
import cn.virde.susan.page.PageByJsoup;
import cn.virde.susan.thread.Engine;

public class Pic {
	public static void main(String[] args) throws IOException {
//		Susan.setConnInfo("192.168.1.211", "spider", "root", "htxWLKJ.2017");
//		Engine engine = new Engine("http://www.store.codyscapes.com/Gallery");
//		engine.start();
		
		Page page = new PageByHtmlUnit("http://www.store.codyscapes.com/Gallery");
		
		System.out.println(page.getHtml());
		
	}
}

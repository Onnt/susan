package cn.blacard.test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import cn.blacard.nymph.String.StringTool;
import cn.blacard.susan.page.Page;

public class LinksGet {
	/**
	 * 获取页面中href中包含的连接
	 * 返回的都是直接能用的链接
	 * @author Blacard
	 * @Create 2016年12月8日 上午11:50:53
	 * @param page 页面
	 * @return 返回页面内直接能用的链接集合
	 * @throws IOException
	 */
	protected HashSet<String> getHref(Page page) throws IOException{
		//正则表达式 匹配href
		List<String> list = StringTool.getStringsByReg(page.getHtml(), "href=([\"\"\'])[\\x21-\\x7ea-zA-Z0-9]{0,1000}([\"\"\'])");
		HashSet<String> newSet = new HashSet<String>();
		
		for(String str : list){
			//减去href=""
			str = str.substring(6, str.length()-1);
//			str = pinJie(page,str);
			if(str!=null){
				newSet.add(str);
			}
		}
		return newSet;
	}
}

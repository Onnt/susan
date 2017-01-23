package cn.blacard.gosong;

import cn.blacard.susan.page.Page;

public class Test {
	public static void main(String[] args) {
		Page page = new Page("http://bizhi.4493.com/chuangyijingwu/1024x768_black/hot_1.html");
		System.out.println(page.getHtml());
		
		for(String str : page.getUrls()){
			System.out.println(str);
		}
	}
	
	/**
	 * 测试字符
	 * @author Blacard
	 * @Create 2017年1月23日 下午2:24:08
	 * @param args
	 */
	public static void main2(String[] args) {
		String path = "http://baidu.com/ddfg/sdf";
		String str = "../yun";
		
		if(!path.endsWith("/")){
			path = path.substring(0, path.lastIndexOf("/")+1);
			System.out.println(path+str);
		}else{
			System.out.println(path+str);
		}
	}
}

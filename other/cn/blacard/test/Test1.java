package cn.blacard.test;

import java.io.IOException;
import java.util.Set;

import cn.blacard.nymph.String.StringTool;
import cn.blacard.susan.page.Page;

public class Test1 {
	public static void main(String[] args) throws IOException {

		test2();
		test3();
//		test4();
	}
	

	
	public static void test2(){
		Page page = new Page("http://shenqu.yy.com");
		for(String s : page.getUrls()){
			if(test4(s).equals(page.getHost())){

				System.out.println(s);
			}
		}
	}	
	public static void test3(){
		
	}	
	public static String test4(String page){
		return StringTool.getStringByReg(page, 
				"(http|ftp|https):\\/\\/([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}");	
	}
}

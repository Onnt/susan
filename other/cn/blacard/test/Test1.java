package cn.blacard.test;

import java.io.IOException;
import java.util.Set;

import cn.blacard.susan.page.Page;

public class Test1 {
	public static void main(String[] args) throws IOException {

		test2();
		test3();
		test4();
	}
	

	
	public static void test2(){
		Page page = new Page("http://shenqu.yy.com");
		for(String s : page.getUrls()){
			System.out.println(s);
		}
	}	
	public static void test3(){
		
	}	
	public static void test4(){
		
	}
}

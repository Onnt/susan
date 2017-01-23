package cn.blacard.gosong;

import cn.blacard.susan.page.Page;

public class Test {
	public static void main_(String[] args) {
		Page page = new Page("http://www.ppmsg.net/");
		System.out.println(page.getHtml());
		
		for(String str : page.getUrls()){
			System.out.println(str);
		}
	}
	
	public static void main(String[] args) {
		Page page = new Page("http://www.ppmsg.net/iue/sd/sdf/");
		
		System.out.println(relativePathDeal(page,"../../../../ohehe"));
		
//		String str = "sdfasd.asdf/asdf/";
//		System.out.println(str.substring(0, str.lastIndexOf("/")));
		
//		System.out.println(relativeCurrPathDeal(page,"./ohehe"));
	}
	
	private static String relativePathDeal(Page page,String str){ 
		//将href链接分成前 和 后
		String str_front = str.substring(0,str.lastIndexOf("../")+3);
		String str_end = str.replace(str_front, "");
		
		//截取页面地址 后半部分(pageUrl_end)
		String pageUrl_end = null;
		String pageUrl = page.getPageUrl();
		String pageUrl_replace = pageUrl.replace("//", "");
		if(pageUrl_replace.contains("/")){
			pageUrl_end = pageUrl_replace.substring(
					pageUrl_replace.indexOf("/"), //startIndex 
					pageUrl_replace.length() //endIndex
					);
			if(pageUrl_end.indexOf("/")==pageUrl_end.lastIndexOf("/")){
				//页面链接只有一级深度（根目录），不能完成跳转（sample: http://baidu.com/ http://baidu.com/index.html）
				return null;
			}
			if(pageUrl_end == null || pageUrl_end.equals("") ){
				//页面链接只有一级深度（根目录），不能完成跳转（sample: http://baidu.com）
				return null;
			}
		}else{
			//页面链接只有一级深度（根目录），不能完成跳转（sample: http://baidu.com）
			return null;
		}
		
		String[] strs =  str_front.split("/");
		String[] pageUrl_ends = pageUrl_end.split("/");
		
		int pageUrl_ends_length = pageUrl_ends.length-1;
		if(!pageUrl_end.endsWith("/")) pageUrl_ends_length--;
		if(strs.length > pageUrl_ends_length){
			//href 大于 页面深度，不能完成跳转（sample: http://baidu.com/map.index href="../index.html"）
			return null;
		}
		for(String s : strs){
			if(s.equals("..")){
				pageUrl_end = pageUrl_end.substring(0, pageUrl_end.lastIndexOf("/")); 
				pageUrl_end = pageUrl_end.substring(0, pageUrl_end.lastIndexOf("/"))+"/"; 
			}
		}
		
		return page.getHost()+pageUrl_end+str_end;
	}
	
	private static String relativeCurrPathDeal(Page page,String str){
		String pageUrl = page.getPageUrl();
		String respStr = null;
		//判断页面链接是否以“/”结尾
		if(pageUrl.endsWith("/")){
			str = str.replace("./", "");
			respStr = pageUrl + str;
		}else{
			pageUrl = pageUrl.substring(0,pageUrl.lastIndexOf("/")+1);
			str = str.replace("./", "");
			respStr = pageUrl+str;
		}
		
		return respStr;
	}
}

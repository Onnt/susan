package cn.blacard.susan.page;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import cn.blacard.nymph.String.StringTool;


/**
 * 对获取到的page进行处理
 * 
 * @author  Blacard
 * @邮箱：blacard@163.com
 * @date 创建时间：2016年7月11日 下午11:06:32 
  */
public class PageDeal {
	
	/**
	 * 
	 * @author Blacard
	 * @Create 2017年1月23日 下午2:11:55
	 * @param page
	 * @return
	 * @throws IOException
	 */
	public static HashSet<String> getSrc(Page page) throws IOException{
		List<String> list = StringTool.getStringsByReg(page.getHtml(), "src=([\"\"\'])[\\x21-\\x7ea-zA-Z0-9]{0,1000}([\"\"\'])");
		HashSet<String> newSet = new HashSet<String>();
		for(String s : list){
			s = s.substring(5, s.length()-1);
			s = pinJie(page,s);
			if(s!=null){
				newSet.add(s);
			}
		}
		return newSet;
	}
	
	/**
	 * 获取页面中href中包含的连接
	 * 返回的都是直接能用的链接
	 * @author Blacard
	 * @Create 2016年12月8日 上午11:50:53
	 * @param page 页面
	 * @return 返回页面内直接能用的链接集合
	 * @throws IOException
	 */
	public static HashSet<String> getHref(Page page) throws IOException{
		//正则表达式 匹配href
		List<String> list = StringTool.getStringsByReg(page.getHtml(), "href=([\"\"\'])[\\x21-\\x7ea-zA-Z0-9]{0,1000}([\"\"\'])");
		HashSet<String> newSet = new HashSet<String>();
		
		for(String str : list){
			//减去href=""
			str = str.substring(6, str.length()-1);
			
			//对href中的链接进行处理
			str = pinJie(page,str);

			if(str!=null){
				//对爬取得链接范围进行限制，只爬取指定hostname范围的链接
				if(getHostName(str).equals(page.getHost())){
					newSet.add(str);
				}
			}
		}
		return newSet;
	}
	
	/**
	 * 根据页面的链接 获取到 hostName
	 * @return
	 */
	public static String getHostName(Page page){
		return StringTool.getStringByReg(page.getPageUrl(), 
				"(http|ftp|https):\\/\\/([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}");
	}

	/**
	 * 根据页面的链接 获取到 hostName
	 * @return
	 */
	public static String getHostName(String page){
		return StringTool.getStringByReg(page, 
				"(http|ftp|https):\\/\\/([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}");
	}
	
	/**
	 * 判断并处理href里的内容
	 * 优化方向：增加处理相对路径的能力，比如../../ 或者./
	 * 
	 * @param page WangZhi，这个参数里面的host要用
	 * @param str href里的内容
	 * @return 处理过的链接，可以直接使用，不保证有效性。
	 * 如果内容明显无效，返回null
	 */
	@Deprecated
	private static String pinJie(Page page,String str){
		//判断非null，非空
		if(str==null) return null;
		str = str.replaceAll(" ","");
		if(str.equals("")) return null;
		
		//判断是否包含无用字符
		if(str.contains("javascript")||str.contains("favicon.ico")
				||str.contains(".js") || str.contains(".css")){
			return null;
		}
		
		//判断str是否是一个网络链接
		if(str.matches("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?.*")){
			return correcting(str);
		}

		//如果是绝对路径，加上域名组成完整的路径
		if(str.toCharArray()[0]=='/'){
			return correcting(page.getHost()+str);
		}
		//判断str是否是相对路径
//		if(str.startsWith("./")||str.startsWith("../")){
		
//		}
		String path = page.getPageUrl();
		if(!path.endsWith("/")){
			path = path.substring(0, path.lastIndexOf("/")+1);
		}
		str = path+str;
		if(str.matches("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?.*")){
			return correcting(str);
		}
		return null;
	}
	
	/**
	 * 校正链接，防止出现格式不正确的链接
	 * @param str 被校正的链接
	 * @return 校正过的链接
	 */
	@Deprecated
	private static String correcting(String str){
		String resp = StringTool.getStringByReg(str, "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?");
		return resp;
	}
}

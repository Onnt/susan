package cn.virde.susan.url;

import java.util.List;

import cn.virde.susan.bean.Url;

/**
 * 网址管理
 * @author Virde
 * @date 2018年4月20日 下午2:46:49
 */
public interface UrlManager {
	/**
	 * 判断是否存在该Url 
	 * @author Virde
	 * @date 2018年4月20日 下午2:46:42
	 * @param url
	 */
	public boolean isExistUrl(String url) throws Exception;
	
	/**
	 * 增加一个Url
	 * @author Virde
	 * @date 2018年4月20日 下午2:47:55
	 * @param url
	 * @return 
	 */
	public int insert(String url)  throws Exception;
	
	/**
	 * 一次插入多条Url
	 * @author Virde
	 * @date 2018年4月20日 下午2:49:37
	 * @param urls
	 * @return 
	 */
	public int insert(List<String> urls)  throws Exception;
	
	/**
	 * 更新url的状态
	 * upd + 1 
	 * time = current_timestamp
	 * @author Virde
	 * @date 2018年4月20日 下午3:28:31
	 * @param url
	 * @param status
	 * @return 
	 */
	public int updateState(String url ,int state)  throws Exception;
	public int updateState(Url url)  throws Exception;
	
	/**
	 * 更新列表url的状态 
	 * @author Virde
	 * @date 2018年4月20日 下午3:28:53
	 * @param list
	 * @param status
	 * @return 
	 */
	public int updateState(List<Url> list)  throws Exception;
	
	/**
	 * 获取Urls
	 * @author Virde
	 * @date 2018年4月20日 下午3:29:18
	 * @param status
	 * @param limit
	 */
	public List<Url> getUrlsByStatusLimit(int status,int limit)  throws Exception;
	/**
	 * 
	 * @author Virde
	 * @date 2018年4月23日 上午10:11:37
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<Url> getErrorUrls(int limit) throws Exception ;
	/**
	 * 获取待提取链接
	 * 获取规则： <br>
	 * 1. 按upd降速获取state > -1 的链接<br>
	 * 2. 5%几率获取状态码< 0链接  <br>
	 * 3. 获取链接之后，将连接更新至已提取状态<br>
	 * @author Virde
	 * @date 2018年4月20日 下午6:08:13
	 * @param limit
	 * @return
	 */
	public List<Url> getExtractUrl(int limit) throws Exception ;
	
	/**
	 * 更新链接为已提取状态
	 * 更新规则：
	 * 1. 状态码 +1
	 * 2. 状态码小于0 则取反
	 * @author Virde
	 * @date 2018年4月20日 下午6:12:57
	 * @param url
	 * @return
	 */
//	public int updateStateToExtract(Url url) throws Exception ;
//	public int updateStateToExtract(List<Url> list)  throws Exception ;
	
	/**
	 * 获取待解析链接
	 * 获取规则：
	 * 1. 状态码 = 1
	 * @author Virde
	 * @date 2018年4月20日 下午6:24:50
	 * @param limit
	 * @return
	 */
	public List<Url> getAnalyUrl(int limit) throws Exception;
	/**
	 * 更新链接为已解析状态
	 * 更新规则：
	 * 1 状态吗 +2 
	 * @author Virde
	 * @date 2018年4月20日 下午6:21:11
	 * @param url
	 * @return
	 */
	public int updateStateToAnaly(Url url) throws Exception;
	public int updateStateToAnaly(List<Url> url) throws Exception;
	
	/**
	 * 更新链接为错误状态
	 * 更新规则：
	 * 1. 状态码 < 0，则 -1
	 * 2. 状态吗 < -10 , 则进行删除  
	 * @author Virde
	 * @date 2018年4月20日 下午6:18:28
	 * @param url
	 * @return
	 */
	public int updateStateToError(Url url) throws Exception;
	public int updateStateToError(List<Url> url) throws Exception;
	
	/**
	 * 删除链接
	 * @author Virde
	 * @date 2018年4月20日 下午6:19:35
	 * @param url
	 * @return
	 */
	public int deleteUrl(String url) throws Exception ;
	public int deleteUrl(List<Url> list) throws Exception ;
}

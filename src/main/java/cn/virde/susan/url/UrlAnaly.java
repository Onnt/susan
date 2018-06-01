package cn.virde.susan.url;

import java.util.List;

import cn.virde.susan.bean.Url;

/**
 * 
 * @author Virde
 * @date 2018年5月29日 下午3:15:32
 */
public interface UrlAnaly {
	
	/**
	 * 获取待解析的链接，并修改链接状态
	 * @author Virde
	 * @date 2018年5月29日 下午3:15:38
	 * @param num
	 * @return
	 */
	List<Url> getAnalyUrls(int num) ;
}

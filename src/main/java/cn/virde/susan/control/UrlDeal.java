package cn.virde.susan.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.virde.nymph.util.Log;
import cn.virde.susan.bean.Url;
import cn.virde.susan.page.Page;
import cn.virde.susan.page.impl.PageByJsoup;
import cn.virde.susan.setting.Option;
import cn.virde.susan.url.UrlManager;
import cn.virde.susan.url.impl.UrlManagerMysql;

/**
 * 这个类只做两件事情
 * 1. 从给的url当中获取更多url
 * 2. 将获取到的url给地址管理模块，存储
 * @author Virde
 * @date 2018年4月23日 下午2:06:08
 */
public class UrlDeal extends Thread{
	
	private Url url ;
	private List<String> list ;
	private UrlManager um ;
	private Option option ;

	public UrlDeal(Option option,Url url) {
		this.option = option ;
		um = new UrlManagerMysql(option);
		this.url = url ;
	}
	
	@Override
	public void run() {
		try {
			long start = System.currentTimeMillis() ;
			if(!url.getUrl().contains(option.getRangeHost())) {
				Log.info("该链接不在爬取范围内：From：" + url);
				return ;
			}
			getUrls();
			saveUrls();
			long end = System.currentTimeMillis() ;
			Log.info("链接爬取耗时："+ (end - start)/1000 +"s，From：" + url.getUrl());
		} catch (Exception e) {
			Log.error("爬取链接时出现异常，From："+url, e);
			try {
				um.updateStateToError(url) ;
			} catch (Exception e1) {
				Log.error("更新url状态为error时发生异常，From：" + url.getUrl(), e1);
			}
		}
	}
	public void getUrls() throws IOException{
		Page page = new PageByJsoup(url.getUrl()) ;
		Set<String> hrefs = page.getHrefs() ;
		list = new ArrayList<String>();
		list.addAll(hrefs);
	}
	
	public void saveUrls() {
		int line = 0 ;
		for(String url : list) {
			line += saveUrl(url);
		}
		Log.info("爬取到 "+line+" / "+list.size()+" 条有效链接，From："+url.getUrl());
	}
	
	private int saveUrl(String url) {
		try {
			return um.insert(url) ;
		} catch (Exception e) {
			Log.error("保存url："+url+"时出现异常，From："+url, e);
			return 0 ;
		}
	}
}
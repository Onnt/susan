package cn.virde.susan.control;

import java.util.List;

import cn.virde.nymph.util.Log;
import cn.virde.susan.bean.Url;
import cn.virde.susan.setting.Option;
import cn.virde.susan.url.UrlManager;
import cn.virde.susan.url.impl.UrlManagerMysql;

/**
 * 
 * @author Virde
 * @date 2018年4月23日 下午2:04:10
 */
public class Control {
	
	private Option option ;
	private UrlManager um;
	public Control(Option option) {
		this.option = option ;
		um = new UrlManagerMysql(option);
	}
	
	public void start() {
		if(option.getHost()!=null) {
			Url url = new Url() ;
			url.setUrl(option.getHost());
			new UrlDeal(option,url).start();
		}
		while(true) {
			cycle();
		}
	}
	
	public void cycle() {
		try {
			List<Url> list = um.getExtractUrl(option.getLineNumber());
			for(Url url : list) {
				new UrlDeal(option,url).start();
			}
		} catch (Exception e) {
			Log.error("遇到严重错误，系统退出", e);
		} 
	}
}

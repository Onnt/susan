package cn.virde.susan;

import cn.virde.nymph.common.info.ValidInfo;
import cn.virde.nymph.db.mysql.MySql;
import cn.virde.nymph.util.Log;
import cn.virde.susan.control.Control;
import cn.virde.susan.setting.Option;
import cn.virde.susan.url.impl.UrlManagerMysql;

/**
 * 
 * @author Virde
 * @date 2018年4月20日 下午2:08:08
 */
public class Susan {

	public Option option = new Option();
	
	// 启动引擎
	public void start() {
		Log.alert("程序启动，开始自检");
		
		Log.alert("正在验证 引擎参数 设置");
		validOption();
		Log.alert("引擎参数 验证通过");
		
		Log.alert("开始验证 数据库链接");
		 validConnInfo();
		 Log.alert("数据库链接 验证通过");
		 
		 Log.alert("创建数据表如果表不存在");
		createTableIfNotExists();
		
		Log.alert("启动程序完成，开始爬取链接");
		Control ct = new Control(option);
		ct.start();
	}
	private void validOption() {
		ValidInfo info = option.valid() ;
		if( ! info.isOk()) {
			for(ValidInfo vi :info.getResult()) {
				Log.alert(vi.getInfo());
			}
			Log.alert("参数错误，程序启动失败");
			System.exit(0);
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void validConnInfo() {
		MySql mysql = new MySql(option.getConnInfo()) ;
		ValidInfo info = mysql.valid() ;
		if( ! info.isOk()) {
			for(ValidInfo vi :info.getResult()) {
				Log.alert(vi.getInfo());
			}
			Log.alert("数据库链接参数有误，请检查确认");
			System.exit(0);
		}
	}
	
	private void createTableIfNotExists() {
		UrlManagerMysql umm = new UrlManagerMysql(option);
		umm.createTableIfNotExists();
	}
}

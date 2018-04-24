package cn.virde.susan.test.control;

import cn.virde.susan.bean.Url;
import cn.virde.susan.control.UrlDeal;
import cn.virde.susan.setting.Option;
import junit.framework.TestCase;

public class UrlDealTest  extends TestCase{
	
	public void testRun() {
		Option option = new Option();
		option.setConnInfo("virde.cn", "db_chun", "blacard", "235_is_Pass");
		option.setTableName("tableName");
		
		UrlDeal ud = new UrlDeal(option,new Url("http://ifeve.com/java-threadpool/"));
		ud.start();
	}
}

package cn.virde.susan.test;

import cn.virde.susan.Susan;
import cn.virde.susan.setting.Option;
import cn.virde.susan.url.impl.SustainedMysqlUrlManager;

public class Lofter {

	public static Option option ;
    public static void main( String[] args ){
		Option option = new Option();
		Susan susan = new Susan();
		option.setConnInfo("47.97.194.188", "db_spider", "spider", "Im_spider_1");
		option.setTableName("virde_deskzol");
		option.setLineNumber(10);
		option.setUm(new SustainedMysqlUrlManager(option));
//		option.setSleepTime(3000);
		option.setHost("http://desk.zol.com.cn");
		option.setRangeHost("desk.zol.com.cn");
		susan.setOption(option);
		susan.start();
    }
    
}

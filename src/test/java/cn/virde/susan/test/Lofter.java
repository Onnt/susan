package cn.virde.susan.test;

import cn.virde.susan.Susan;
import cn.virde.susan.setting.Option;

public class Lofter {
	public static void main(String[] args) {
			Option option = new Option();
			Susan susan = new Susan();
			option.setConnInfo("virde.cn", "db_chun", "blacard", "235_is_Pass");
			option.setTableName("virde_lofter");
			option.setLineNumber(15);
			option.setHost("http://virde.lofter.com");
			option.setRangeHost("lofter.com");
			susan.option  = option ;
			susan.start();
 	}
}

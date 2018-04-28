package cn.virde.susan.test;

import java.io.IOException;

import cn.virde.nymph.Nym;
import cn.virde.susan.Susan;
import cn.virde.susan.setting.Option;

public class Lofter {
	public static void main(String[] args) throws IOException {
			Option option = new Option();
			Susan susan = new Susan();
			option.setConnInfo("47.97.194.188", "db_spider", "spider", "Im_spider_1");
			option.setTableName("virde_lofter");
			option.setLineNumber(15);
			option.setHost("http://virde.lofter.com");
			option.setRangeHost("lofter.com");
			susan.option  = option ;
			susan.start();
 	}
}

package cn.virde.susan.test.url;

import java.util.List;

import cn.virde.susan.Susan;
import cn.virde.susan.bean.Url;
import cn.virde.susan.url.impl.UrlManagerMysql;
import junit.framework.TestCase;

public class UrlManagerTest extends TestCase{

	public void testIsExist() throws Exception {
		Susan susan = new Susan();
		susan.option.setConnInfo("virde.cn", "db_chun", "blacard", "235_is_Pass");
		susan.option.setTableName("tableName");
		
		UrlManagerMysql umm = new UrlManagerMysql(susan.option);
		List<Url> list = umm.getExtractUrl(3);
		for(Url u : list) {
			System.out.println(u.getUrl());
		}
	}
	
	
}

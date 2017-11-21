package cn.virde.susan;

import cn.virde.nymph.db.ConnInfo;
import cn.virde.nymph.enums.common.DBStyle;

public class Config {

	public static String db_address = "192.168.1.211";
	public static String db_name = "spider";
	public static String db_user = "root";
	public static String db_pass = "htxWLKJ.2017";
	
	public static void set(String address,String name,String user,String pass){
		db_address = address;
		db_name = name;
		db_user = user;
		db_pass = pass;
	}
	
	public static ConnInfo getDBConnInfo(){
		return new ConnInfo(
				DBStyle.MYSQL,
				db_address,
				db_name,
				db_user,
				db_pass
				);
	}
	
	public static ConnInfo getMongoDBConnInfo() {
		return new ConnInfo().setIp("localhost").setDbName("spider").setStyle(DBStyle.MONGO);
	}
}

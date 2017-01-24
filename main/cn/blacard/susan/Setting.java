package cn.blacard.susan;

import cn.blacard.dbopera.constant.DBStyle;
import cn.blacard.dbopera.para.DBConnectPara;

public class Setting {
	public static String db_address = "127.0.0.1";
	public static String db_name = "spider";
	public static String db_user = "root";
	public static String db_pass = "yunbin";
	
	public static DBConnectPara getDBConnectPara(){
		return new DBConnectPara(
				DBStyle.MYSQL,
				db_address,
				db_name,
				db_user,
				db_pass
				);
	}
}

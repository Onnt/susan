package cn.virde.susan;

import cn.virde.nymph.db.mongo.MongoUtil;
import cn.virde.nymph.db.mysql.MySql;
import cn.virde.susan.pojo.UrlsEntity;

public class DB {
	public final static MySql<UrlsEntity> mysql = new MySql<UrlsEntity>(Config.getDBConnInfo());
	public final static MongoUtil mongo = new MongoUtil(Config.getMongoDBConnInfo());
	
	public static void main(String[] args) {
		System.out.println( 8 % 10);
	}
}

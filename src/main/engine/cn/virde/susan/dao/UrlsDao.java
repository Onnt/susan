package cn.virde.susan.dao;

import java.sql.SQLException;
import java.util.List;

import cn.virde.nymph.db.exception.NymDBException;
import cn.virde.nymph.db.mysql.MySql;
import cn.virde.susan.Susan;
import cn.virde.susan.pojo.UrlsEntity;

public  class UrlsDao {
	private static MySql<UrlsEntity> mysql = new MySql<UrlsEntity>(Susan.getConnInfo());
	static{
		try {
			mysql.executeSQL("Create Table If Not Exists "+Susan.tableName+"(url varchar(2000),extract varchar(10),analy varchar(10),state varchar(100),createTime timestamp default CURRENT_TIMESTAMP());", null);
		} catch (NymDBException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 插入一个无状态的url
	 * @author Blacard
	 * @Create 2017年1月22日 下午2:33:23
	 * @param url
	 * @throws SQLException 
	 * @throws NymDBException 
	 */
	public static void insert(String url){
		try {
			if(!isExist(url)){
				mysql.executeSQL("insert into "+Susan.tableName+"(url) value(?)", new Object[]{url});
			}
		} catch (NymDBException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断一个url是否存在
	 * @author Blacard
	 * @Create 2017年1月22日 下午2:35:12
	 * @param url
	 * @throws SQLException 
	 * @throws NymDBException 
	 */
	public static boolean isExist(String url) throws NymDBException, SQLException{
		List<UrlsEntity> respList = mysql.query("select * from "+Susan.tableName+" where url = ?", new Object[]{url}, UrlsEntity.class);
		if(respList !=null && respList.size() == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 更改URL的状态为“已提取”
	 * 
	 * @author Blacard
	 * @Create 2017年1月22日 下午2:51:02
	 * @param url
	 * @throws SQLException 
	 * @throws NymDBException 
	 */
	public static void updateExtract(String url) throws NymDBException, SQLException{
		mysql.executeSQL("update "+Susan.tableName+" set extract = 'yes' where url =? ", new Object[]{url});
		updateCreTime(url);
	}
	
	/**
	 * 更改URL的状态为 “已分析”
	 * @author Blacard
	 * @Create 2017年1月22日 下午2:51:44
	 * @param url
	 * @throws SQLException 
	 * @throws NymDBException 
	 */
	public static void updateAnaly(String url) throws NymDBException, SQLException{
		mysql.executeSQL("update "+Susan.tableName+" set analy = 'yes' where url =? ", new Object[]{url});
		updateCreTime(url);
	}
	public static void markError(String url){
		try {
			markState(url,"error");
		} catch (NymDBException | SQLException e) {
			e.printStackTrace();
		}
	}
	public static void markState(String url,String state) throws NymDBException, SQLException{
		mysql.executeSQL("update "+Susan.tableName+" set analy = 'yes',extract='yes',state=? where url =? ", new Object[]{state,url});
	}
	/**
	 * 获取一个等待提取的url
	 * @author Blacard
	 * @Create 2017年1月22日 下午2:58:26
	 * @return
	 * @throws SQLException 
	 * @throws NymDBException 
	 */
	public static String getExtractUrl(){
		String url = null ;
		try {
			List<UrlsEntity> respList = mysql.query("select * from "+Susan.tableName+" where extract is null or (TIMESTAMPDIFF(HOUR,createTime,NOW()) > 3 and state is null) limit 1", null,UrlsEntity.class);
			if(respList != null && respList.size() == 1){
				url = respList.get(0).getUrl();
				updateExtract(url);
			}
			return url;
		} catch (NymDBException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取一个等待分析的url
	 * @author Blacard
	 * @Create 2017年1月22日 下午2:58:26
	 * @return
	 * @throws SQLException 
	 * @throws NymDBException 
	 */
	public static String getAnalyUrl() throws NymDBException, SQLException{
		return getAnalyUrl("select * from "+Susan.tableName+" where analy is null limit 1");
	}
	
	/**
	 * 支持定制sql语句
	 * @param sql
	 * @return
	 * @throws SQLException 
	 * @throws NymDBException 
	 */
	public static String getAnalyUrl(String sql) throws NymDBException, SQLException {
		List<UrlsEntity> respList = mysql.query(sql, null,UrlsEntity.class);
		if(respList != null && respList.size() == 1){
			String url = respList.get(0).getUrl();
			updateAnaly(url);
			updateCreTime(url);
			return url;
		}else{
			return null;
		}
	}

	public static String getUrlBySql(String sql) throws NymDBException, SQLException {
		List<UrlsEntity> respList = mysql.query(sql, null,UrlsEntity.class);
		if(respList != null && respList.size() > 0){
			return respList.get(0).getUrl();
		}else{
			return null;
		}
	}
	
	public static void updateCreTime(String url) throws NymDBException, SQLException {
		mysql.executeSQL("update "+Susan.tableName+" set createTime = CURRENT_TIMESTAMP() where url = ?", new Object[] {url});
	}
}

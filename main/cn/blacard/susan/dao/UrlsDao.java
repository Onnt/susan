package cn.blacard.susan.dao;

import java.util.List;

import cn.blacard.susan.Susan;

public class UrlsDao {
	static{
		Susan.query.executeSQL("Create Table If Not Exists urls(url varchar(1000) primary key,extract varchar(10),analy varchar(10),state varchar(100),createTime timestamp default CURRENT_TIMESTAMP());", null);
	}
	/**
	 * 插入一个无状态的url
	 * @author Blacard
	 * @Create 2017年1月22日 下午2:33:23
	 * @param url
	 */
	public static void insert(String url){
		if(!isExist(url)){
			Susan.query.executeSQL("insert into urls(url) value(?)", new Object[]{url});
		}
	}
	
	/**
	 * 判断一个url是否存在
	 * @author Blacard
	 * @Create 2017年1月22日 下午2:35:12
	 * @param url
	 */
	public static boolean isExist(String url){
		List<UrlsEntity> respList = Susan.query.query("select * from urls where url = ?", new Object[]{url}, UrlsEntity.class);
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
	 */
	public static void updateExtract(String url){
		Susan.query.executeSQL("update urls set extract = 'yes' where url =? ", new Object[]{url});
	}
	
	/**
	 * 更改URL的状态为 “已分析”
	 * @author Blacard
	 * @Create 2017年1月22日 下午2:51:44
	 * @param url
	 */
	public static void updateAnaly(String url){
		Susan.query.executeSQL("update urls set analy = 'yes' where url =? ", new Object[]{url});
	}
	
	public static void markError(String url,String state){
		Susan.query.executeSQL("update urls set analy = 'yes',extract='yes',state=? where url =? ", new Object[]{state,url});
	}
	/**
	 * 获取一个等待提取的url
	 * @author Blacard
	 * @Create 2017年1月22日 下午2:58:26
	 * @return
	 */
	public static String getExtractUrl(){
		List<UrlsEntity> respList = Susan.query.query("select * from urls where extract is null limit 1", null,UrlsEntity.class);
		if(respList != null && respList.size() == 1){
			String url = respList.get(0).getUrl();
			updateExtract(url);
			return url;
		}else{
			return null;
		}
	}
	
	/**
	 * 获取一个等待分析的url
	 * @author Blacard
	 * @Create 2017年1月22日 下午2:58:26
	 * @return
	 */
	public static String getAnalyUrl(){
		List<UrlsEntity> respList = Susan.query.query("select * from urls where analy is null limit 1", null,UrlsEntity.class);
		if(respList != null && respList.size() == 1){
			String url = respList.get(0).getUrl();
			updateAnaly(url);
			return url;
		}else{
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getAnalyUrl());
	}
}

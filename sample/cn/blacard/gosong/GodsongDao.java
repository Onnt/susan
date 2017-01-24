package cn.blacard.gosong;

import java.util.List;

import cn.blacard.dbopera.query.QueryOften;
import cn.blacard.susan.Setting;
import cn.blacard.susan.Susan;
import cn.blacard.susan.dao.UrlsEntity;

public class GodsongDao {

	private static QueryOften<GodsongEntity> query = new QueryOften<GodsongEntity>(Setting.getDBConnectPara());
	
	/**
	 * 获取一个等待分析的url
	 * @author Blacard
	 * @Create 2017年1月22日 下午2:58:26
	 * @return
	 */
	public static String getDownUrl(){
		List<GodsongEntity> respList = query.query("select * from godsong where state is null limit 1", null,GodsongEntity.class);
		if(respList != null && respList.size() == 1){
			String url = respList.get(0).getSong();
			updateAnaly(url);
			return url;
		}else{
			return null;
		}
	}
	
	/**
	 * 更改URL的状态为 “已分析”
	 * @author Blacard
	 * @Create 2017年1月22日 下午2:51:44
	 * @param url
	 */
	public static void updateAnaly(String url){
		Susan.query.executeSQL("update godsong set state = 'yes' where song =? ", new Object[]{url});
	}
	public static void main(String[] args) {
		System.out.println(getDownUrl());
	}
}

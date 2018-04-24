package cn.virde.susan.bean;

public class Url {
	public final static int STATE_NORMAL = 1;
	public final static int STATE_ANALY  = 2 ;
	private String url ;
	
	/**
	 * -101 : 链接错误
	 * 1 : 正常状态
	 * 2 : 已解析 
	 */
	private Integer state = 1 ;
	// 次记录更新次数;
	private Integer upd = 0 ;
	private String time ;
	
	
	
	public Url() {
		super();
	}
	public Url(String url) {
		super();
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getUpd() {
		return upd;
	}
	public void setUpd(Integer upd) {
		this.upd = upd;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}

package cn.virde.susan.bean;

public class Url {
	public final static int STATE_NORMAL = 1;
	public final static int STATE_ANALY  = 2 ;
	private Integer id ;
	private String url ;
	
	/**
	 * -101 : 链接错误
	 * 1 : 正常状态
	 * 2 : 已解析 
	 */
	private Integer state = 1 ;
	private String time ;
	
	
	
	public Url() {
		super();
	}
	public Url(String url) {
		super();
		this.url = url;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setId(String id) {
		this.id = Integer.parseInt(id);
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Url other = (Url) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		Url url = new Url("");
		url.setId(32);
		System.out.println(url.equals(new Url("")));
	}
}

package cn.virde.susan.url.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.virde.susan.bean.Url;
import cn.virde.susan.url.UrlManager;

/**
 * 
 * @author Virde
 * @date 2018年6月1日 下午2:50:34
 */
public class UrlManagerSet extends UrlManager{

	public static volatile Set<Url> urls = new HashSet<Url>();
	
	@Override
	public boolean isExistUrl(String url) throws Exception {
		return urls.contains(new Url(url));
	}

	@Override
	public int insert(String url) throws Exception {
		if( ! isExistUrl(url))
			return urls.add(new Url(url)) ? 1 : 0 ;
		else
			return 0 ;
	}

	@Override
	public int updateState(String url, int state) throws Exception {
		Url u = new Url(url);
		urls.remove(u);
		u.setState(state);
		return urls.add(u) ? 1 : 0 ;
	}

	@Override
	public int updateUrl(Url url) throws Exception {
		urls.remove(url);
		return urls.add(url) ? 1 : 0 ;
	}
	
	@Override
	public List<Url> getUrlsByStatusLimit(int status, int limit) throws Exception {
		return null ;
	}

	@Override
	public List<Url> getErrorUrls(int limit) throws Exception {
		
		return null;
	}

	@Override
	public List<Url> getExtractUrl(int limit) throws Exception {
		
		return null;
	}

	@Override
	public List<Url> getAnalyUrl(int limit) throws Exception {
		
		return null;
	}

	@Override
	public int updateStateToAnaly(Url url) throws Exception {
		
		return 0;
	}

	@Override
	public int updateStateToAnaly(List<Url> url) throws Exception {
		
		return 0;
	}

	@Override
	public int updateStateToError(Url url) throws Exception {
		
		return 0;
	}

	@Override
	public int updateStateToError(List<Url> url) throws Exception {
		
		return 0;
	}

	@Override
	public int deleteUrl(String url) throws Exception {
		
		return 0;
	}

	@Override
	public int deleteUrl(List<Url> list) throws Exception {
		
		return 0;
	}

	
	public static void main(String[] args) {
		Url url = new Url("23");
		url.setState(1);
		urls.add(new Url("23"));
		url.setState(2);
		urls.add(new Url("23"));
		url.setState(3);
		urls.add(new Url("23"));
		
		urls.forEach((e)->System.out.print(e.getState()));
	}
}

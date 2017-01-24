package cn.blacard.gosong;

import java.io.IOException;
import java.util.Date;

import cn.blacard.nymph.String.StringTool;
import cn.blacard.nymph.date.NymTime;
import cn.blacard.nymph.net.down.DownFromUrl;
import cn.blacard.nymph.random.NumberRandom;
import cn.blacard.susan.dao.UrlsDao;
import cn.blacard.susan.page.Page;
import cn.blacard.susan.page.PageDeal;

public class Test {
	public static void main(String[] args) throws IOException {

		String analyUrl = UrlsDao.getAnalyUrl();
		while(analyUrl!=null&&!analyUrl.equals("")){
			
			Page page = new Page(analyUrl);
			
			DownFromUrl down = new DownFromUrl();
			for(String str : PageDeal.getSrc(page)){
				String fileName = NymTime.toString(new Date(), "YYYYMMDD-HH-mm-ss")+NumberRandom.getRandom(0, 1000000)+"."+StringTool.getSuffix(str);
				try{
					down.downFromUrl(str, fileName, "D://NymDownLoad/");
				}catch(Exception e){
					System.out.println("出现异常");
				}
			}
			analyUrl = UrlsDao.getAnalyUrl();
		}
	}
	
	public static void main_(String[] args) throws IOException {

		DownFromUrl down = new DownFromUrl();
		String fileName ="hehe.mp4";
		down.downFromUrl("http://godsong.bs2dl.yy.com/dmNmYTE3Njk0ZDJhNWUyZTE2NDg3ZjA1NTZlMmJhZWQ0MTU5NjAxMTYzOG1i", fileName, "D://");
	}

}

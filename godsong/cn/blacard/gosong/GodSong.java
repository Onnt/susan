package cn.blacard.gosong;

import cn.blacard.susan.thread.Engine;

public class GodSong {
	public static void main(String[] args) {
		Engine engine = new Engine("http://shenqu.yy.com");
		engine.start();
	}
}

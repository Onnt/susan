package cn.virde.susan.setting;

public class Event {
	public Func startFunc ;
	public Func endFunc ;
	public void onStart(Func func) {
		this.startFunc = func ;
	}
	public void onEnd(Func func) {
		this.endFunc = func ;
	}
	
}

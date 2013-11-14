package com.ts.example.ITSListener;

public class Constant {	
	UI iuInstance;
	private String url = "http://www.testingsoft.com/";
	
	public Constant(){}
	
	public Constant(UI iuInstance){
		this.iuInstance = iuInstance;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}	
}
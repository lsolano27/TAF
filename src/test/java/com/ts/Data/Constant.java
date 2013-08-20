package com.ts.Data;

public class Constant {	
	private static final int TIME_OUT = 70;
	private static String URL = "http://www.testingsoft.com/";
	
	public static String getURL() {
		return URL;
	}
	public static void setURL(String url) {
		URL = url;
	}
	public static int getTimeOut() {
		return TIME_OUT;
	}
}
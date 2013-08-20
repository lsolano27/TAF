package com.ts.Pages;

import org.openqa.selenium.support.PageFactory;

import com.ts.Data.Constant;
import com.ts.commons.FirefoxDriver;

public class UI {
	
	public static FirefoxDriver driver;
	
	public static testingsoftHome getHomePage(){
		driverConfiguration();
		testingsoftHome ts= PageFactory.initElements(driver, testingsoftHome.class);
		return ts;
	}
	
	public static void driverConfiguration(){
		if(driver == null){
			driver = new FirefoxDriver();						
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		}				
		driver.get(Constant.getURL());				
	}
}
package com.ts.Pages;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;

import com.ts.commons.FirefoxDriver;

public class UI {
	
	public static FirefoxDriver driver;
	
	public static testingsoftHome getHomePage() throws IOException{
		driverConfiguration();
		testingsoftHome ts= PageFactory.initElements(driver, testingsoftHome.class);
		return ts;
	}
	
	public static void driverConfiguration() throws IOException{
		if(driver == null){
			driver = new FirefoxDriver();						
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		}				
		driver.get("http://www.testingsoft.com/");				
	}
}
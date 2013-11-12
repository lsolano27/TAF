package com.ts.example.ITSListener;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.PageFactory;

import com.ts.commons.ChromeDriver;
import com.ts.commons.FirefoxDriver;
import com.ts.commons.InternetExplorerDriver;

public class UI {
	private WebDriver driver;	
	
	public UI(String browserType){
		switch (browserType) {
		case BrowserType.CHROME:	
			driver = ChromeDriver.createInstance();
			break;
		case BrowserType.FIREFOX:
			driver = new FirefoxDriver();			
			break;
		case BrowserType.IE:
			driver = InternetExplorerDriver.createInstance();
			break;
		default:
			driver = null;
			break;
		}	
		driver.manage().window().maximize();		
	}
	
	public testingsoftHome getHomePage(){
		driver.get(new Constant().getUrl());
		testingsoftHome home= PageFactory.initElements(driver, testingsoftHome.class);
		home.setUI(this);
		return home;
	}	
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
}
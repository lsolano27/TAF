package com.ts.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;

public class TS_UI {
	private WebDriver driver;
	
	public TS_UI(String browserType) {
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
		driver.manage().deleteAllCookies();
	}
		
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
}
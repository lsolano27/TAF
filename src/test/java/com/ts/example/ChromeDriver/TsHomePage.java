package com.ts.example.ChromeDriver; 

import org.openqa.selenium.WebDriver;

import com.ts.commons.generator.Page;

public class TsHomePage extends Page {

	 public TsHomePage(WebDriver driver) 
	{ 
		 super(driver); 
	} 

	
	public TsHomePage and() {
		return this;
	}

	public TsHomePage then() {
		return this;
	}
}

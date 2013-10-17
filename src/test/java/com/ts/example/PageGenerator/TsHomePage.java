package com.ts.example.PageGenerator; 

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.ts.commons.generator.Page;
import com.ts.commons.generator.PoWebElement;
import org.openqa.selenium.WebDriver;

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

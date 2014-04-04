package com.ts.commons;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TSJavaScriptExecutor {

	public static Object executeScript(WebDriver driver, String script){
		 return ((JavascriptExecutor)driver).executeScript(script);		
	}	
	
	public static Object executeScript(WebDriver driver, String script, WebElement element){
		 return ((JavascriptExecutor)driver).executeScript(script, element);		
	}	
}
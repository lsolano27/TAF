package com.ts.commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class FirefoxDriver extends org.openqa.selenium.firefox.FirefoxDriver  {
	
	public static final int IMPLICT_TIME = 5;
	public static final TimeUnit IMPLICT_TIME_UNIT = TimeUnit.MINUTES;
	
	
	public FirefoxDriver()
	{
		super();
		manage().timeouts().implicitlyWait(IMPLICT_TIME, IMPLICT_TIME_UNIT );
	}
	
	 public void get(String domine, String user, String pass)
     {
		 get("https://"+user+":"+pass+"@"+domine);       
     }

	 
	 public void moveToElement(WebElement element)
	 {
		 Actions builder = new Actions(this); 
		 builder.moveToElement(element).build().perform();
	 }

	 public void doubleClick(WebElement element)
	 {
		 Actions builder = new Actions(this); 
		 builder.doubleClick(element).build().perform();
	 }
	 
	 public void clickAndHold(WebElement element)
	 {
		 Actions builder = new Actions(this); 
		 builder.clickAndHold(element).build().perform();
	 }
	  
	 public void dragAndDrop(WebElement source, WebElement target)
	 {
		 Actions builder = new Actions(this); 
		 builder.dragAndDrop(source, target).build().perform();
	 }

	 public void waitToElementBeVisible(final WebElement element)
	 {
		 new Until() {
			
			@Override
			public boolean execute() {
				return element.isDisplayed();
			}
		};
	 }
	 
	 public void waitToElementBeEnabled(final WebElement element)
	 {
		 new Until() {
			
			@Override
			public boolean execute() {
				return element.isEnabled();
			}
		};
	 }
	 
	 public void waitToElementBeSelected(final WebElement element)
	 {
		 new Until() {
			
			@Override
			public boolean execute() {
				return element.isSelected();
			}
		};
	 }
	 
	 public String getBrowserVersion()
	 {
		 return this.getCapabilities().getVersion();
	 }
	 
	 
}
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
		
		Validator ffValidations= new Validator() {
			
			@Override
			public void Validate() {
	
				int currentVersion = Integer.parseInt(getBrowserVersion().split("\\.")[0]);
				if(currentVersion < 16)
				{
					new RuntimeException("Increase firefox to version 16 or upper, to avoid errors");
				}
			}
		};
		ffValidations.Validate();
		
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

	 public void waitToElementBeVisible(final WebElement... element)
	 {
		TestCaseUtil.wait(new Until() {
			
			@Override
			public boolean execute() {
				for(WebElement ele: element )
				{
					boolean elementIsNotVisible = ! ele.isDisplayed();
					if( elementIsNotVisible )
					{
						return false;
					}
				}
				return true;
			}
		});
	 }
	 
	 public void waitToElementBeEnabled(final WebElement... element)
	 {
		 TestCaseUtil.wait(new Until() {
			@Override
			public boolean execute() {
				for(WebElement ele: element )
				{
					boolean elementIsNotVisible = ! ele.isEnabled();
					if( elementIsNotVisible )
					{
						return false;
					}
				}
				return true;
			}
		});
	 }
	 
	 public void waitToElementBeSelected(final WebElement... element)
	 {
		 TestCaseUtil.wait(new Until() {
			@Override
			public boolean execute() {
				for(WebElement ele: element )
				{
					boolean elementIsNotVisible = ! ele.isSelected();
					if( elementIsNotVisible )
					{
						return false;
					}
				}
				return true;
			}
		});
	 }
	 
	 public String getBrowserVersion()
	 {
		 return this.getCapabilities().getVersion();
	 }
	 
	 
}
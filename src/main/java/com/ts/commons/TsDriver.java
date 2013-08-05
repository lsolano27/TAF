package com.ts.commons;

import org.openqa.selenium.WebElement;

public interface TsDriver {
	
	 public void get(String domine, String user, String pass);

	 
	 public void moveToElement(WebElement element);

	 public void doubleClick(WebElement element);
	 
	 public void clickAndHold(WebElement element);
	  
	 public void dragAndDrop(WebElement source, WebElement target);

	 public void waitToElementBeVisible(final WebElement... element);
	 
	 public void waitToElementBeEnabled(final WebElement... element);
	 
	 public void waitToElementBeSelected(final WebElement... element);
	 
	 public String getBrowserVersion();
	 

}

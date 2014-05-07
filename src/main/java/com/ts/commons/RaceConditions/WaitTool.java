package com.ts.commons.RaceConditions;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.SeleneseTestBase;
import com.ts.commons.TsDriver;

public class WaitTool {

	/**
	 * Wait for elements in the DOM and wait for it to be displayed
	 * @param driver: current driver
	 * @param elements: elements which you are asking if they are present and visible, it could be as many as you want.
	 */
	public static void waitForElementPresentAndVisible(WebDriver driver, final WebElement... elements) {
		
		for (final WebElement element : elements) {
			ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return (Boolean.valueOf(element.isEnabled()) == true && Boolean.valueOf(element.isDisplayed() == true));
				}
			};

			WebDriverWait wait = new WebDriverWait(driver,TsDriver.IMPLICT_TIME * 2);

			try {
				wait.until(expectation);
			} catch (Throwable error) {
				SeleneseTestBase.fail("Fail waiting for element not Present neither Visible");
			}
		}			
	}
	
	/**
	 * Wait for element in the DOM and wait for it to be displayed
	 * @param driver: current driver
	 * @param element: element which you wanna ask if it is not present and visible
	 * @return the same element
	 */
	public static WebElement waitForElementNotPresentAndVisible(WebDriver driver, final WebElement element) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() 
		{
		     public Boolean apply(WebDriver driver) 
		     {		    	 
		    	 return (Boolean.valueOf(element.isEnabled()) == false && Boolean.valueOf(element.isDisplayed() == false));				
		     }
		};

	    WebDriverWait wait = new WebDriverWait(driver, TsDriver.IMPLICT_TIME * 2);
	    
		try 
		{
			wait.until(expectation);
		} 
		catch (Throwable error) 
		{
			SeleneseTestBase.fail("Fail waiting for element not Present neither Visible");
	    }
		return element;		
	}
	
	
	/**
	 * Checks if the element is in the DOM and displayed. 
	 * @param driver: current driver
	 * @param element: element which you wanna ask if it is present and displayed
	 * @return true if element is present and displayed, false otherwise.
	 */
	public static boolean isElementPresentAndDisplay(WebDriver driver, WebElement element) {
		try {			
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	/**
	 * wait for element to have an expected style
	 * @param driver: current driver
	 * @param element: element which you wanna get the style
	 * @param style: the style which you are waiting for
	 */
	public static void waitForStyle(WebDriver driver, final WebElement element, final String style) 
	{
	    ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() 
		{
		     public Boolean apply(WebDriver driver) 
		     {		    	 
	    	 	String returnedValue = element.getAttribute("style");	    
				return String.valueOf(returnedValue).equals(style);				
		     }
		};

	    WebDriverWait wait = new WebDriverWait(driver, TsDriver.IMPLICT_TIME * 2);
	    
		try 
		{
			wait.until(expectation);
		} 
		catch (Throwable error) 
		{
			SeleneseTestBase.fail("Fail waiting for expected style");
	    }
	 }	
	
	/**
	 * Wait for element after browser refresh the current page
	 * @param driver: current driver
	 * @param elements: num of elements you wanna wait for.
	 */
	 public static void waitForElementRefresh(WebDriver driver, WebElement... elements) {
		 driver.navigate().refresh();
		 waitForElementPresentAndVisible(driver, elements);
	 }
	 
	 /**
	  * Wait for text present in a given element
	  * @param driver: current driver
	  * @param element: element to get the text in the site
	  * @param text: expected text
	  * @return: true if the element has the expected text, false otherwise.
	  */
	 public static boolean waitForTextPresentOnElement(WebDriver driver, final WebElement element, final String text) {
		 
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return element.getText().equals(text);
			}
		};

		WebDriverWait wait = new WebDriverWait(driver,TsDriver.IMPLICT_TIME * 2);

		try {
			wait.until(expectation);
			return true;
		} catch (Throwable error) {
			SeleneseTestBase.fail("Fail waiting for Text Present On Element");
			return false;
		}		 
	 }
}
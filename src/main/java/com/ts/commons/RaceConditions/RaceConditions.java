package com.ts.commons.RaceConditions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.SeleneseTestBase;
import com.ts.commons.TSJavaScriptExecutor;
import com.ts.commons.TsDriver;

public class RaceConditions {
	
	static int waitTime = TsDriver.IMPLICT_TIME * 2; 
	private static String script = "";
	private static String typeOfRaceCondition = "";
		
	protected static void waitForRaceCondition(WebDriver driver, String failMessage, final String script) 
	{
	    ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() 
		{
		     public Boolean apply(WebDriver driver) 
		     {
		    	 
	    	 	String returnedValue = TSJavaScriptExecutor.executeScript(driver, script).toString();
	    	
		    	switch (getTypeOfRaceCondition()) 
		    	{
					case RaceConditionType.VAADIN:
						return Boolean.parseBoolean(returnedValue) == false;
					case RaceConditionType.JQUERY:
						return Integer.parseInt(returnedValue) == 0;
					case RaceConditionType.AJAX:
						return Integer.parseInt(returnedValue) == 0;
					default:
						return String.valueOf(returnedValue).equals("complete");
				}		    	
		     }
		};

	    WebDriverWait wait = new WebDriverWait(driver, waitTime);
	    
		try 
		{
			wait.until(expectation);
			System.out.println(getTypeOfRaceCondition());
		} 
		catch (Throwable error) 
		{
			SeleneseTestBase.fail(failMessage);
	    }
	 }
	
	public static void waitForAllRaceConditions(WebDriver driver)
	{
		VAADIN.waitForVaadin(driver);
		JQUERY.waitForJquery(driver);
		JAVASCRIPT.waitForJavaScript(driver);
		AJAX.waitForAjax(driver);
	}

	public static String getScript() 
	{
		return script;
	}

	public static void setScript(String script) 
	{
		RaceConditions.script = script;
	}

	public static String getTypeOfRaceCondition() 
	{
		return typeOfRaceCondition;
	}

	public static void setTypeOfRaceCondition(String typeOfRaceCondition) 
	{
		RaceConditions.typeOfRaceCondition = typeOfRaceCondition;
	}	
}
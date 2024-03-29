package com.ts.commons.RaceConditions;

import org.openqa.selenium.WebDriver;

import com.ts.commons.TSJavaScriptExecutor;

public class JQUERY extends RaceConditions{
	
	public static void waitForJquery(final WebDriver driver)
	{
		setScript("return $.active");	
		setTypeOfRaceCondition(RaceConditionType.JQUERY);
		
		if(isThisAppUsingJquery(driver))
		{				
			waitForRaceCondition(driver, "", getScript());			
		}		
	}	
	
	private static boolean isThisAppUsingJquery(WebDriver driver)
	{
		try 
		{
			TSJavaScriptExecutor.executeScript(driver, getScript()).toString();
			return true;
		} 
		catch (Exception e) 
		{
			return false;
		}
	}
}
package com.ts.commons.RaceConditions;

import org.openqa.selenium.WebDriver;

import com.ts.commons.TSJavaScriptExecutor;

public class AJAX extends RaceConditions{

	public static void waitForAjax(final WebDriver driver)
	{				
		setScript("return jQuery.active");		
		setTypeOfRaceCondition(RaceConditionType.AJAX);
		
		if(isThisAppUsingAjax(driver))
		{				
			waitForRaceCondition(driver, "", getScript());			
		}		
	}	
	
	private static boolean isThisAppUsingAjax(WebDriver driver){
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
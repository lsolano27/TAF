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
		
	protected static void waitForRaceCondition(WebDriver driver, String failMessage, final String script) {
	    ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
		     public Boolean apply(WebDriver driver) {
		    	 String returnedValue = TSJavaScriptExecutor.executeScript(driver, script).toString();
		    	 
		    	 if(script.contains("vaadin")){
		    		 return Boolean.parseBoolean(returnedValue) == false;
		    	 }else{
		    		 return Integer.parseInt(returnedValue) == 0;
		    	 }
		     }
	    };

	    WebDriverWait wait = new WebDriverWait(driver, waitTime);
	    try {
	     wait.until(expectation);
	    } catch (Throwable error) {
	    	SeleneseTestBase.fail(failMessage);
	    }
	 }

	public static String getScript() {
		return script;
	}

	public static void setScript(String script) {
		RaceConditions.script = script;
	}	
}
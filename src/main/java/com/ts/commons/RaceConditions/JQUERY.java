package com.ts.commons.RaceConditions;

import org.openqa.selenium.WebDriver;

import com.ts.commons.TSJavaScriptExecutor;

public class JQUERY extends RaceConditions{
	
	public static void waitForJquery(final WebDriver driver){
		setScript("return $.active");		
		if(isThisAppUsingJquery(driver)){				
				waitForRaceCondition(driver, "", getScript());			
		}		
	}	
	
	/*ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
		public Boolean apply(WebDriver driver) {
			return Integer.parseInt(((JavascriptExecutor) driver).executeScript("return $.active").toString()) == 0;
		}
	};

	WebDriverWait wait = new WebDriverWait(driver, 70);
	try {
		wait.until(expectation);
	} catch (Throwable error) {
		SeleneseTestBase
				.fail(failMessage);
	}*/
	
	private static boolean isThisAppUsingJquery(WebDriver driver){
		try {
			TSJavaScriptExecutor.executeScript(driver, "return $.active").toString();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
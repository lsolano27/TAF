package com.ts.commons.RaceConditions;

import org.openqa.selenium.WebDriver;

public class JAVASCRIPT extends RaceConditions{

	public static void waitForJavaScript(final WebDriver driver){
		setScript("return document.readyState");	
		setTypeOfRaceCondition(RaceConditionType.JAVASCRIPT);
		waitForRaceCondition(driver, "", getScript());
	}	
	
}

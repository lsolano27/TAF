package com.ts.commons.RaceConditions;

import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.WebDriver;

import com.ts.commons.TSJavaScriptExecutor;

public class VAADIN extends RaceConditions{
	
	@SuppressWarnings("unchecked")
	public static void waitForVaadin(final WebDriver driver){
		
		if(isThisAppUsingVaadin(driver)){
			Map<String, String> list = (Map<String, String>) TSJavaScriptExecutor.executeScript(driver, "return vaadin.clients");
			
			for (Entry<String, String> client : list.entrySet()) {
				setScript("return vaadin.clients[\""+ client.getKey() +"\"].isActive()");
				waitForRaceCondition(driver, "", getScript());
			}
		}		
	}
	
	private static boolean isThisAppUsingVaadin(WebDriver driver){
		try {
			TSJavaScriptExecutor.executeScript(driver, "return vaadin.clients").toString();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
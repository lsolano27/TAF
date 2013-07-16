package com.ts.commons.generator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PoWebElement{
	protected String attributeBy;
	protected String attributeValue;
	private static final String[] criterias = {"id","name"};
	private PoJavaScriptExecutor poJavaScriptExecutor;
	
	public PoWebElement(WebDriver driver)
	{
		poJavaScriptExecutor = new PoJavaScriptExecutor(driver);
	}
			
	public PoWebElement(String attributeBy, String attributeValue){
		this.attributeBy = attributeBy;
		this.attributeValue = attributeValue;
	}
	
	public String getBy(WebElement element){
		for (String criteria : criterias) {
			if( ! element.getAttribute(criteria).equals(null) && 
					! element.getAttribute(criteria).equals("")){
				return criteria;
			}
		}		
		return "xpath";			
	}
	
	public String getValue(WebElement element, String by){
		if( ! by.equals("xpath")){
			return element.getAttribute(by);			
		}else if(by.equals("xpath")){
			return poJavaScriptExecutor.getElementByXPath(element).replace("\"", "'");
		}else {
			return "";
		}	
	}
}
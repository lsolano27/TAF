package com.ts.commons.generator;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PoPageObject {
	
	private ArrayList<PoWebElement> lista = new ArrayList<PoWebElement>();	
	private Page plantilla ;
	private WebDriver driver;
	private PoJavaScriptExecutor poJavaScriptExecutor;
	
	public PoPageObject(WebDriver driver, Page page) 
	{
		this.driver = driver;
		plantilla = page;
		poJavaScriptExecutor = new PoJavaScriptExecutor(driver);
	}
	
	protected List<WebElement> getFrames(){
		List<WebElement> frames = new ArrayList<WebElement>();
		if(poJavaScriptExecutor.getNumOfElements("function get(){return document.getElementsByTagName(\"frame\");};return get();", "Frame") > 0){
			frames.addAll(driver.findElements(By.cssSelector("frame")));
		}			
		frames.addAll(getiFrames());		
		return frames;
	}
	
	private List<WebElement> getiFrames(){
		List<WebElement> iframes = new ArrayList<WebElement>();
		if(poJavaScriptExecutor.getNumOfElements("function get(){return document.getElementsByTagName(\"iframe\");};return get();", "iFrame") > 0){
			iframes.addAll(driver.findElements(By.cssSelector("iframe")));
		}		
		return iframes;
	}
	
	protected PoPageObject getInputTypeTextElements(){
		if(poJavaScriptExecutor.getNumOfElements("function get(){return document.getElementsByTagName(\"input\");};return get();", "text") > 0){
			List<WebElement> inputTypeText = driver.findElements(By.cssSelector("input[type=text]"));
			generateElementsDeclarations(inputTypeText);
		}		
		return this;
	}
	
	protected PoPageObject getInputTypePassElements(){
		if(poJavaScriptExecutor.getNumOfElements("function get(){return document.getElementsByTagName(\"input\");};return get();", "password") > 0){
			List<WebElement> inputTypePass = driver.findElements(By.cssSelector("input[type=password]"));
			generateElementsDeclarations(inputTypePass);
		}		
		return this;
	}
	
	protected PoPageObject getInputTypeRadioElements(){
		if(poJavaScriptExecutor.getNumOfElements("function get(){return document.getElementsByTagName(\"input\");};return get();", "radio") > 0){
			List<WebElement> inputTypeRadio = driver.findElements(By.cssSelector("input[type=radio]"));
			generateElementsDeclarations(inputTypeRadio);
		}		
		return this;
	}
	
	protected PoPageObject getInputTypeButtomElements(){
		if(poJavaScriptExecutor.getNumOfElements("function get(){return document.getElementsByTagName(\"input\");};return get();", "button") > 0){
			List<WebElement>inputTypeButton = driver.findElements(By.cssSelector("input[type=button]"));
			generateElementsDeclarations(inputTypeButton);
		}		
		return this;
	}
	
	protected PoPageObject getInputTypeSubmitElements(){
		if(poJavaScriptExecutor.getNumOfElements("function get(){return document.getElementsByTagName(\"input\");};return get();", "submit") > 0){
			List<WebElement>inputTypeSubmit = driver.findElements(By.cssSelector("input[type=submit]"));
			generateElementsDeclarations(inputTypeSubmit);
		}		
		return this;
	}
	
	protected PoPageObject getInputTypeCheckElements(){
		if(poJavaScriptExecutor.getNumOfElements("function get(){return document.getElementsByTagName(\"input\");};return get();", "checkbox") > 0){
			List<WebElement>inputTypeCheck = driver.findElements(By.cssSelector("input[type=checkbox]"));
			generateElementsDeclarations(inputTypeCheck);
		}		
		return this;
	}
		
	protected PoPageObject getSelectElements(){
		if(poJavaScriptExecutor.getNumOfElements("function get(){return document.getElementsByTagName(\"select\");};return get();", "select") > 0){
			List<WebElement> selects = driver.findElements(By.cssSelector("select"));
			generateElementsDeclarations(selects);
		}		
		return this;
	}
	
	protected PoPageObject getButtonsElements(){
		if(poJavaScriptExecutor.getNumOfElements("function get(){return document.getElementsByTagName(\"button\");};return get();", "buttonTag") > 0){
			List<WebElement> buttons = driver.findElements(By.cssSelector("button"));
			generateElementsDeclarations(buttons);
		}		
		return this;
	}
	
	protected PoPageObject getLinkElementsByATag(){
		if(poJavaScriptExecutor.getNumOfElements("function get(){return document.getElementsByTagName(\"a\");};return get();", "links") > 0){
			List<WebElement> links = driver.findElements(By.cssSelector("a"));
			generateElementsDeclarations(links);
		}		
		return this;
	}
	
	protected PoPageObject getTextAreaElements(){
		if(poJavaScriptExecutor.getNumOfElements("function get(){return document.getElementsByTagName(\"textarea\");};return get();", "textarea") > 0){
			List<WebElement> textAreas = driver.findElements(By.cssSelector("textarea"));
			generateElementsDeclarations(textAreas);
		}		
		return this;
	}
	
	private void generateElementsDeclarations(List<WebElement> webElements){
		for (WebElement element : webElements) {
			PoWebElement newWebElement = new PoWebElement(driver);
			newWebElement.attributeBy = newWebElement.getBy(element);
			newWebElement.attributeValue = newWebElement.getValue(element, newWebElement.attributeBy);
			newWebElement.attributeTag = newWebElement.getTagName(element);
			lista.add(newWebElement);							
		}
	}
	
	protected PoPageObject storageWebElementDeclarations(int frame){
		if(lista.size() > 0){
			plantilla.storageWebElements(driver, lista, frame);	
		}		
		lista.clear();
		return this;
	}
	
	protected PoPageObject createClassFile(String newPageName){
		plantilla.generateFile(newPageName);					
		return this;
	}
}
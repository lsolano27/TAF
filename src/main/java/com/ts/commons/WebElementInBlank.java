package com.ts.commons;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;




public class WebElementInBlank implements org.openqa.selenium.WebElement{

	private String value="";
	
	public void click() {
		throw new RuntimeException("Blanck Element hasn't click");
		
	}

	public void submit() {
		throw new RuntimeException("Blanck Element hasn't submit");
		
	}

	public void sendKeys(CharSequence... keysToSend) {
		for(CharSequence var : keysToSend)
		{
			this.value+=var;
		}
	}

	public void clear() {
		this.value =  "";		
	}

	public String getTagName() {
		throw new RuntimeException("Blanck Element hasn't tagname");
	}

	public String getAttribute(String name) {
		throw new RuntimeException("Blanck Element hasn't atrribute");
	}

	public boolean isSelected() {
		throw new RuntimeException("Blanck Element hasn't isSelected");
	}

	public boolean isEnabled() {
		throw new RuntimeException("Blanck Element hasn't isEnabled");
	}

	public String getText() {
		return this.value;
	}

	public List<WebElement> findElements(By by) {
		throw new RuntimeException("Blanck Element hasn't findElements");
	}

	public WebElement findElement(By by) {
		throw new RuntimeException("Blanck Element hasn't finElement");
	}

	public boolean isDisplayed() {
		throw new RuntimeException("Blanck Element hasn't isDisplayed");
	}

	public Point getLocation() {
		throw new RuntimeException("Blanck Element hasn't getLocation");
	}

	public Dimension getSize() {
		throw new RuntimeException("Blanck Element hasn't getSize");
	}

	public String getCssValue(String propertyName) {
		throw new RuntimeException("Blanck Element hasn't Cssvalue");
	}
	

}

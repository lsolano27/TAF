package com.ts.commons.generator;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


 public class PoGenetaror{	
	private String newPageName;
	private WebDriver driver;
	
	
	public PoGenetaror(WebDriver driver) {
		this.driver = driver;
	}

	public PoGenetaror setNewPageName(String newPageName) {
		this.newPageName = newPageName;
		return this;
	}
	
	public PoGenetaror generatePageObject(Page  page){
		PoPageObject aPage = new PoPageObject(driver, page);		
		List<WebElement> frames = aPage.getFrames();		
		
		generate(
			aPage, frames
		)
		.createFile(
			aPage.createClassFile(newPageName)
		);	
		return this;		
	}	
	
	private PoGenetaror generate(PoPageObject aPage, List<WebElement> frames){
		process(aPage, 0);
		
		for (WebElement frame : frames) {
			driver.switchTo().frame(frame);
			process(aPage, frames.size()).moveToDefaultContent();
		}		
		return this;				
	}
	
	private PoGenetaror process(PoPageObject aPage, int numOfFrame){
		return
		getElements(
				aPage
					.getInputTypeTextElements()
					.getInputTypePassElements()
					.getInputTypeRadioElements()
					.getInputTypeButtomElements()
					.getButtonsElements()
					.getInputTypeSubmitElements()
					.getInputTypeCheckElements()		
					.getSelectElements()					
					.getLinkElementsByATag()
					.getTextAreaElements()
		).storageDeclarations(
				aPage
					.storageWebElementDeclarations(numOfFrame)
		);
	}
		
	private PoGenetaror getElements(PoPageObject aPage){
		return this;
	}
	
	private PoGenetaror createFile(PoPageObject aPage){
		return this;		
	}
	
	private PoGenetaror moveToDefaultContent(){	
		driver.switchTo().defaultContent();
		return this;		
	}
	
	private PoGenetaror storageDeclarations(PoPageObject aPage){
		return this;		
	}
}
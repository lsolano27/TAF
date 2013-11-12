package com.ts.example.ITSListener; 

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ts.commons.generator.PoWebElement;

public class testingsoftHome extends ExampleBasePage {

	/* 
	* Don't delete or change this method, or the object will be generated again.
	*/ 
	public PoWebElement[] getPredifinedWebElements()
	{
		return new PoWebElement[] {new PoWebElement("xpath",".//*[@id='menu']/li[1]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[1]/ul[1]/li[1]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[2]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[3]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[4]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[4]/ul[1]/li[1]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[4]/ul[1]/li[2]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[4]/ul[1]/li[3]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[5]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[5]/ul[1]/li[1]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[5]/ul[1]/li[2]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[5]/ul[1]/li[3]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[5]/ul[1]/li[4]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[6]/a[1]"), new PoWebElement("xpath",".//*[@id='slider']/ul[1]/li[1]/a[1]"), new PoWebElement("xpath",".//*[@id='slider']/ul[1]/li[2]/a[1]"), new PoWebElement("xpath",".//*[@id='slider']/ul[1]/li[3]/a[1]"), new PoWebElement("xpath",".//*[@id='slider']/ul[1]/li[4]/a[1]"), new PoWebElement("xpath",".//*[@id='slider']/ul[1]/li[5]/a[1]"), new PoWebElement("xpath",".//*[@id='slider']/ul[1]/li[6]/a[1]"), new PoWebElement("xpath",".//*[@id='slider']/ul[1]/li[7]/a[1]"), new PoWebElement("xpath",".//*[@id='slider']/ul[1]/li[8]/a[1]"), new PoWebElement("xpath",".//*[@id='slider']/ul[1]/li[9]/a[1]"), new PoWebElement("xpath",".//*[@id='prevBtn']/a[1]"), new PoWebElement("xpath",".//*[@id='nextBtn']/a[1]"), new PoWebElement("xpath",".//*[@id='footer']/div[1]/div[3]/a[1]"), new PoWebElement("xpath",".//*[@id='footer']/div[1]/div[3]/a[2]")};
	}

	@FindBy(xpath = ".//*[@id='menu']/li[2]/a[1]")
	WebElement aCcontactUS;

	public ContactUsPage aCcontactUS(){
		aCcontactUS.click();
		return PageFactory.initElements(uiInstance.getDriver(), ContactUsPage.class); 
	}	
	
	public testingsoftHome and() {
		return this;
	}

	public testingsoftHome then() {
		return this;
	}
}

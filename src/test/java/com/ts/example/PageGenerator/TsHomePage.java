package com.ts.example.PageGenerator; 

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.ts.commons.generator.Page;
import com.ts.commons.generator.PoWebElement;
import org.openqa.selenium.WebDriver;

public class TsHomePage extends Page {

	 public TsHomePage(WebDriver driver) 
	{ 
		 super(driver); 
	} 

	/* 
	* Don't delete or change this method, or the object will be generated again.
	*/ 
	public PoWebElement[] getPredifinedWebElements()
	{
		return new PoWebElement[] {new PoWebElement("name","fname"), new PoWebElement("name","company"), new PoWebElement("name","position"), new PoWebElement("name","phone"), new PoWebElement("name","email"), new PoWebElement("id","recaptcha_response_field"), new PoWebElement("name","submit"), new PoWebElement("id","homestate"), new PoWebElement("id","d_d"), new PoWebElement("id","d_daddr"), new PoWebElement("xpath",".//*[@id='dir_wp_template']/div[1]/div[2]/div[1]/input[1]"), new PoWebElement("id","iwdiraddr"), new PoWebElement("id","iwsn"), new PoWebElement("id","d_sub"), new PoWebElement("id","saveplacebutton"), new PoWebElement("xpath",".//*[@id='dirfrm']/div[2]/div[1]/input[1]"), new PoWebElement("xpath",".//*[@id='snfrm']/div[1]/div[1]/input[1]"), new PoWebElement("xpath",".//*[@id='earth_button']/div[2]/div[1]/div[2]/div[1]/input[1]"), new PoWebElement("xpath",".//*[@id='hmtctl']/div[1]/div[3]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/input[1]"), new PoWebElement("xpath",".//*[@id='tbo_checkbox_jstemplate']/input[1]")};
	}

	@FindBy(name = "fname")
	WebElement inputFname;

	@FindBy(name = "company")
	WebElement inputCompany;

	@FindBy(name = "position")
	WebElement inputPosition;

	@FindBy(name = "phone")
	WebElement inputPhone;

	@FindBy(name = "email")
	WebElement inputEmail;

	@FindBy(id = "recaptcha_response_field")
	WebElement inputRecaptchaResponseField;

	@FindBy(name = "submit")
	WebElement inputSubmit;


	//*******************
	//*	FRAME OR IFRAME *
	//******************* 

	@FindBy(id = "homestate")
	WebElement inputHomestate;

	@FindBy(id = "d_d")
	WebElement inputDD;

	@FindBy(id = "d_daddr")
	WebElement inputDDaddr;

	@FindBy(xpath = ".//*[@id='dir_wp_template']/div[1]/div[2]/div[1]/input[1]") // this element is not enable or not displayed
	WebElement var6;

	@FindBy(id = "iwdiraddr")
	WebElement inputIwdiraddr;

	@FindBy(id = "iwsn")
	WebElement inputIwsn;

	@FindBy(id = "d_sub")
	WebElement buttonDSub;

	@FindBy(id = "saveplacebutton")
	WebElement buttonSaveplacebutton;

	@FindBy(xpath = ".//*[@id='dirfrm']/div[2]/div[1]/input[1]") // this element is not enable or not displayed
	WebElement var5;

	@FindBy(xpath = ".//*[@id='snfrm']/div[1]/div[1]/input[1]") // this element is not enable or not displayed
	WebElement var4;

	@FindBy(xpath = ".//*[@id='earth_button']/div[2]/div[1]/div[2]/div[1]/input[1]") // this element is not enable or not displayed
	WebElement var3;

	@FindBy(xpath = ".//*[@id='hmtctl']/div[1]/div[3]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/input[1]") // this element is not enable or not displayed
	WebElement var2;

	@FindBy(xpath = ".//*[@id='tbo_checkbox_jstemplate']/input[1]") // this element is not enable or not displayed
	WebElement var;

	public TsHomePage and() {
		return this;
	}

	public TsHomePage then() {
		return this;
	}
}

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
		return new PoWebElement[] {new PoWebElement("name","fname"), new PoWebElement("name","company"), new PoWebElement("name","position"), new PoWebElement("name","phone"), new PoWebElement("name","email"), new PoWebElement("id","recaptcha_response_field"), new PoWebElement("name","submit"), new PoWebElement("name","country"), new PoWebElement("id","preferredContact"), new PoWebElement("xpath",".//*[@id='menu']/li[1]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[1]/ul[1]/li[1]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[2]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[3]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[4]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[4]/ul[1]/li[1]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[4]/ul[1]/li[2]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[4]/ul[1]/li[3]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[5]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[5]/ul[1]/li[1]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[5]/ul[1]/li[2]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[5]/ul[1]/li[3]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[5]/ul[1]/li[4]/a[1]"), new PoWebElement("xpath",".//*[@id='menu']/li[6]/a[1]"), new PoWebElement("xpath",".//*[@id='info']/p[5]/a[1]"), new PoWebElement("xpath",".//*[@id='body']/div[1]/p[1]/small[1]/a[1]"), new PoWebElement("id","recaptcha_reload_btn"), new PoWebElement("id","recaptcha_switch_audio_btn"), new PoWebElement("id","recaptcha_switch_img_btn"), new PoWebElement("id","recaptcha_whatsthis_btn"), new PoWebElement("xpath",".//*[@id='recaptcha_privacy']/a[1]"), new PoWebElement("xpath",".//*[@id='footer']/div[1]/div[3]/a[1]"), new PoWebElement("xpath",".//*[@id='footer']/div[1]/div[3]/a[2]"), new PoWebElement("name","comments"), new PoWebElement("id","homestate"), new PoWebElement("id","d_d"), new PoWebElement("id","d_daddr"), new PoWebElement("xpath",".//*[@id='dir_wp_template']/div[1]/div[2]/div[1]/input[1]"), new PoWebElement("id","iwdiraddr"), new PoWebElement("id","iwsn"), new PoWebElement("id","d_sub"), new PoWebElement("id","saveplacebutton"), new PoWebElement("xpath",".//*[@id='dirfrm']/div[2]/div[1]/input[1]"), new PoWebElement("xpath",".//*[@id='snfrm']/div[1]/div[1]/input[1]"), new PoWebElement("xpath",".//*[@id='earth_button']/div[2]/div[1]/div[2]/div[1]/input[1]"), new PoWebElement("xpath",".//*[@id='hmtctl']/div[1]/div[3]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/input[1]"), new PoWebElement("xpath",".//*[@id='tbo_checkbox_jstemplate']/input[1]"), new PoWebElement("id","saveplacechoices"), new PoWebElement("xpath",".//*[@id='inner']/a[1]"), new PoWebElement("xpath",".//*[@id='infoarea']/div[2]/div[1]/a[1]"), new PoWebElement("xpath",".//*[@id='infoarea']/div[2]/div[1]/a[2]"), new PoWebElement("id","logolink_inline"), new PoWebElement("xpath",".//*[@id='logocontrol']/a[1]"), new PoWebElement("id","mapmaker-link"), new PoWebElement("id","streetview-rap-link"), new PoWebElement("xpath",".//*[@id='copyright']/div[1]/span[1]/a[3]"), new PoWebElement("xpath",".//*[@id='copyright']/div[2]/a[1]"), new PoWebElement("id","d_close"), new PoWebElement("xpath",".//*[@id='travel_modes_div']/a[1]"), new PoWebElement("xpath",".//*[@id='dir_rev']/a[1]"), new PoWebElement("id","add_dest"), new PoWebElement("id","d_options_show"), new PoWebElement("id","d_options_hide"), new PoWebElement("xpath",".//*[@id='d_tr_warning']/a[1]"), new PoWebElement("xpath",".//*[@id='panel1000']/div[1]/a[1]"), new PoWebElement("id","msimportlink"), new PoWebElement("id","msdoneb"), new PoWebElement("id","mssaveb"), new PoWebElement("id","mseditb"), new PoWebElement("xpath",".//*[@id='panel1000']/div[3]/div[1]/div[1]/div[3]/div[2]/span[3]/a[1]"), new PoWebElement("id","msrl"), new PoWebElement("id","mscl"), new PoWebElement("id","mskd"), new PoWebElement("id","mskl"), new PoWebElement("id","flag_link"), new PoWebElement("id","suck_lhp_link"), new PoWebElement("xpath",".//*[@id='lhsf']/div[1]/a[2]"), new PoWebElement("xpath",".//*[@id='lhsf']/div[1]/a[3]"), new PoWebElement("xpath",".//*[@id='lhsf']/div[2]/a[1]"), new PoWebElement("xpath",".//*[@id='lhsf']/div[2]/a[2]"), new PoWebElement("xpath",".//*[@id='dirfrm']/div[2]/div[2]/a[1]"), new PoWebElement("xpath",".//*[@id='snfrm']/div[1]/div[2]/a[1]"), new PoWebElement("xpath",".//*[@id='saveplace']/div[2]/a[1]"), new PoWebElement("id","pushsaveplace"), new PoWebElement("id","pushstp"), new PoWebElement("id","dct"), new PoWebElement("id","dcf"), new PoWebElement("id","pushdir"), new PoWebElement("id","pushsn"), new PoWebElement("id","zoomlink"), new PoWebElement("xpath",".//*[@id='basics']/div[3]/span[3]/span[1]/a[1]"), new PoWebElement("xpath",".//*[@id='basics']/div[3]/span[3]/span[1]/a[2]"), new PoWebElement("xpath",".//*[@id='basics']/div[3]/span[3]/span[2]/a[1]"), new PoWebElement("xpath",".//*[@id='basics']/div[3]/a[1]"), new PoWebElement("xpath",".//*[@id='iwoldbusiness']/span[1]/a[1]"), new PoWebElement("xpath",".//*[@id='iwnewbusiness']/span[1]/a[1]"), new PoWebElement("id","ssnottruerestore"), new PoWebElement("id","sshistory"), new PoWebElement("id","ssnottruerap"), new PoWebElement("xpath",".//*[@id='biwtable']/tbody[1]/tr[1]/td[1]/div[1]/span[1]/div[1]/a[1]"), new PoWebElement("xpath",".//*[@id='biwtable']/tbody[1]/tr[1]/td[1]/div[1]/span[1]/div[2]/a[1]"), new PoWebElement("xpath",".//*[@id='iwparentpage']/a[1]"), new PoWebElement("xpath",".//*[@id='biwtable']/tbody[1]/tr[1]/td[1]/div[3]/div[1]/span[2]/a[1]"), new PoWebElement("xpath",".//*[@id='iwhomepage']/a[1]"), new PoWebElement("xpath",".//*[@id='biwtable']/tbody[1]/tr[1]/td[1]/div[8]/div[1]/a[1]"), new PoWebElement("xpath",".//*[@id='biwtable']/tbody[1]/tr[1]/td[1]/span[1]/a[1]"), new PoWebElement("xpath",".//*[@id='biwtable']/tbody[1]/tr[1]/td[1]/span[1]/a[2]"), new PoWebElement("xpath",".//*[@id='biwtable']/tbody[1]/tr[1]/td[1]/span[2]/a[1]"), new PoWebElement("xpath",".//*[@id='biwtable']/tbody[1]/tr[1]/td[1]/span[2]/span[1]/a[1]"), new PoWebElement("xpath",".//*[@id='biwtable']/tbody[1]/tr[1]/td[1]/span[2]/span[2]/a[1]"), new PoWebElement("id","svthumbnail"), new PoWebElement("id","svcaption"), new PoWebElement("xpath",".//*[@id='nearbyresults']/a[1]"), new PoWebElement("xpath",".//*[@id='nearbyresults']/a[2]")};
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

	@FindBy(name = "country")
	WebElement selectCountry;

	@FindBy(id = "preferredContact")
	WebElement selectPreferredContact;

	@FindBy(xpath = ".//*[@id='menu']/li[1]/a[1]")
	WebElement 'Sorry, please define this variable name';

	@FindBy(xpath = ".//*[@id='menu']/li[2]/a[1]")
	WebElement aCONTACTUS;

	@FindBy(xpath = ".//*[@id='menu']/li[3]/a[1]")
	WebElement aCUSTOMERS;

	@FindBy(xpath = ".//*[@id='menu']/li[4]/a[1]")
	WebElement aWHATISTS;

	@FindBy(xpath = ".//*[@id='menu']/li[5]/a[1]")
	WebElement aWHOISTS;

	@FindBy(xpath = ".//*[@id='menu']/li[6]/a[1]")
	WebElement aHOME;

	@FindBy(xpath = ".//*[@id='info']/p[5]/a[1]")
	WebElement aInfotestingsoftCom;

	@FindBy(xpath = ".//*[@id='body']/div[1]/p[1]/small[1]/a[1]")
	WebElement aTestingSoftware;

	@FindBy(id = "recaptcha_reload_btn")
	WebElement aRecaptchaReloadBtn;

	@FindBy(id = "recaptcha_switch_audio_btn")
	WebElement aRecaptchaSwitchAudioBtn;

	@FindBy(id = "recaptcha_switch_img_btn")
	WebElement aRecaptchaSwitchImgBtn;

	@FindBy(id = "recaptcha_whatsthis_btn")
	WebElement aRecaptchaWhatsthisBtn;

	@FindBy(xpath = ".//*[@id='recaptcha_privacy']/a[1]")
	WebElement aPrivacyTerms;

	@FindBy(xpath = ".//*[@id='footer']/div[1]/div[3]/a[1]")
	WebElement aFacebook;

	@FindBy(xpath = ".//*[@id='footer']/div[1]/div[3]/a[2]")
	WebElement aTwitter;

	@FindBy(name = "comments")
	WebElement textareaComments;


	//*******************
	//*	FRAME OR IFRAME *
	//*******************

	@FindBy(id = "homestate")
	WebElement inputHomestate;

	@FindBy(id = "d_d")
	WebElement inputDD;

	@FindBy(id = "d_daddr")
	WebElement inputDDaddr;

	@FindBy(id = "iwdiraddr")
	WebElement inputIwdiraddr;

	@FindBy(id = "iwsn")
	WebElement inputIwsn;

	@FindBy(id = "d_sub")
	WebElement buttonDSub;

	@FindBy(id = "saveplacebutton")
	WebElement buttonSaveplacebutton;

	@FindBy(id = "saveplacechoices")
	WebElement selectSaveplacechoices;

	@FindBy(xpath = ".//*[@id='inner']/a[1]")
	WebElement aScreenReaderUsersClickHereForPlainHTML;

	@FindBy(id = "logolink_inline")
	WebElement aLogolinkInline;

	@FindBy(xpath = ".//*[@id='logocontrol']/a[1]")
	WebElement 'Sorry, please define this variable name';

	@FindBy(id = "mapmaker-link")
	WebElement aMapmakerlink;

	@FindBy(id = "streetview-rap-link")
	WebElement aStreetviewraplink;

	@FindBy(xpath = ".//*[@id='copyright']/div[2]/a[1]")
	WebElement aTermsOfUse;

	@FindBy(id = "d_close")
	WebElement aDClose;

	@FindBy(id = "add_dest")
	WebElement aAddDest;

	@FindBy(id = "d_options_show")
	WebElement aDOptionsShow;

	@FindBy(id = "d_options_hide")
	WebElement aDOptionsHide;

	@FindBy(id = "msimportlink")
	WebElement aMsimportlink;

	@FindBy(id = "msdoneb")
	WebElement aMsdoneb;

	@FindBy(id = "mssaveb")
	WebElement aMssaveb;

	@FindBy(id = "mseditb")
	WebElement aMseditb;

	@FindBy(id = "msrl")
	WebElement aMsrl;

	@FindBy(id = "mscl")
	WebElement aMscl;

	@FindBy(id = "mskd")
	WebElement aMskd;

	@FindBy(id = "mskl")
	WebElement aMskl;

	@FindBy(id = "flag_link")
	WebElement aFlagLink;

	@FindBy(id = "suck_lhp_link")
	WebElement aSuckLhpLink;

	@FindBy(id = "pushsaveplace")
	WebElement aPushsaveplace;

	@FindBy(id = "pushstp")
	WebElement aPushstp;

	@FindBy(id = "dct")
	WebElement aDct;

	@FindBy(id = "dcf")
	WebElement aDcf;

	@FindBy(id = "pushdir")
	WebElement aPushdir;

	@FindBy(id = "pushsn")
	WebElement aPushsn;

	@FindBy(id = "zoomlink")
	WebElement aZoomlink;

	@FindBy(id = "ssnottruerestore")
	WebElement aSsnottruerestore;

	@FindBy(id = "sshistory")
	WebElement aSshistory;

	@FindBy(id = "ssnottruerap")
	WebElement aSsnottruerap;

	@FindBy(id = "svthumbnail")
	WebElement aSvthumbnail;

	@FindBy(id = "svcaption")
	WebElement aSvcaption;

	public TsHomePage and() {
		return this;
	}

	public TsHomePage then() {
		return this;
	}
}

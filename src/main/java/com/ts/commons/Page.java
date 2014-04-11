package com.ts.commons;

import org.openqa.selenium.WebElement;

public abstract class Page implements Component {
	TS_UI uiInstance;
	
	protected Page continueToPage(WebElement btn, Page page){
		btn.click();
		page.setUI(uiInstance);		
		return page;
	}
	
	public void setUI(TS_UI uiInstance) {
		this.uiInstance = uiInstance;
	}
	
	public TS_UI getUI() {
		return uiInstance;
	}

	public abstract Page and();

	public abstract Page then();	
}
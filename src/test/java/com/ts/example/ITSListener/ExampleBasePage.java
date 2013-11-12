package com.ts.example.ITSListener;

import com.ts.commons.Page;

public class ExampleBasePage extends Page {
	public UI uiInstance;
	
	@Override
	public Page and() {
	return null;
	}

	@Override
	public Page then() {
		return null;
	}
	
	protected ExampleBasePage setUI(UI uiInstance) {
		this.uiInstance = uiInstance;
		return this;
	}
}

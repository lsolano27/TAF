package com.ts.example.ITSListener;

import org.Listener.TSListener;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.ts.commons.Validator;



public class TSListenerTestCase extends TSListener {
	protected UI uiInstance;
	ExampleBasePage currentPage;
	
	@BeforeMethod
	public void beforeMethod(){
		uiInstance = new UI(BrowserType.FIREFOX);
	}

	@Test
	@Parameters("id")
	public void test(@Optional("MiID") String id) {
		using(
				currentPage = uiInstance.getHomePage()
										.aCcontactUS()
		).
		check(
				new Validator() {
					
					@Override
					public void Validate() {
						
					}
				}
		);
	}
}
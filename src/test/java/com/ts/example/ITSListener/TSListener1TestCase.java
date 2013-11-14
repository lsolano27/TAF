package com.ts.example.ITSListener;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ts.commons.TestCaseUtil;
import com.ts.commons.Validator;



public class TSListener1TestCase extends TestCaseUtil{
	protected UI uiInstance;
	ExampleBasePage currentPage;
	
	@BeforeTest
	public void beforeMethod(){
		uiInstance = new UI(BrowserType.FIREFOX);
	}

	@Test
	@Parameters(value="tcId")
	public void test(@Optional("MyID_#1") String id) {
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
	
	@AfterTest
	public void afterMethod(){
		uiInstance.getDriver().quit();
	}	
}
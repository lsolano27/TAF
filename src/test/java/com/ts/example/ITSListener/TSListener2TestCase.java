package com.ts.example.ITSListener;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ts.commons.TestCaseUtil;
import com.ts.commons.Validator;



public class TSListener2TestCase extends TestCaseUtil {
	protected UI uiInstance;
	ExampleBasePage currentPage;
	
	@BeforeMethod
	public void beforeMethod(){
		uiInstance = new UI(BrowserType.FIREFOX);
	}

	@Test
	@Parameters("tcId")
	public void test(@Optional("MyID_#2") String id) {
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
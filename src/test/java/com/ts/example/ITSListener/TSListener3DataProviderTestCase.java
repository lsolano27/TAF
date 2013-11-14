package com.ts.example.ITSListener;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ts.commons.DataSourceXls;
import com.ts.commons.TestCaseUtil;
import com.ts.commons.Validator;



public class TSListener3DataProviderTestCase extends TestCaseUtil {
	protected UI uiInstance;
	ExampleBasePage currentPage;
	
	@BeforeTest
	public void beforeTest(){
		uiInstance = new UI(BrowserType.FIREFOX);
	}
	
	@AfterTest
	public void afterMethod(){
		uiInstance.getDriver().quit();
	}
	
	@DataProvider
	public Object[][] data() throws BiffException, IOException {
		return new DataSourceXls("ParameterListener.xls").getData(0, 2);
	}

	@Test(dataProvider = "data")
	@Parameters(value="tcId")
	public void test(@Optional("ff") String tcId, String name, String lastName, String ID, String address) {
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
package com.ts.TestCases.dataSource;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ts.Pages.ContactUsPage;
import com.ts.Pages.UI;
import com.ts.commons.DataSourceXls;
import com.ts.commons.Page;
import com.ts.commons.TestCaseUtil;

public class DataSouceContactUs extends TestCaseUtil {	
	Page currentPage;
		
	@DataProvider
	public Object[][] data() throws BiffException, IOException {
		return new DataSourceXls("contactUsData.xls").getData();
	}
	
	@BeforeClass
	private void initElements(){			
		using( 
			currentPage = UI.getHomePage()
							.then()
							.aCcontactUS()								
		)
		.and()
		.check(  
				((ContactUsPage) currentPage).isLoad()
		);
	}	
	
	@Test(dataProvider = "data")
	public void test(String name, String company, String position, String phone, String mail, 
					 String country, String meansOfContact, String comments) throws Exception{	
		using(   
				((ContactUsPage) currentPage)
								.and()
								.fillContactUs(name, company, position, phone, mail, country, meansOfContact, comments)
								.then()
								.resetPage()
		)
		.and()
		.check( 
				((ContactUsPage) currentPage).isLoad()
		);
	}	

	@AfterClass
	public void tearDown() throws Exception {
		UI.driver.quit();		
	}
}
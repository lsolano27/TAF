package com.ts.PageGenerator;

import org.testng.annotations.Test;

import com.ts.commons.FirefoxDriver;
import com.ts.commons.TestCaseUtil;

public class TestCase extends TestCaseUtil{
	
	@Test
	public void test()
	{
		FirefoxDriver ffDriver = new FirefoxDriver();
		ffDriver.get("http://testingsoft.com/contact_us");
		new TsHomePage(ffDriver);
		
	}
}

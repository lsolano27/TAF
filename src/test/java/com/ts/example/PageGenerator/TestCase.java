package com.ts.example.PageGenerator;

import java.io.IOException;

import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;

import com.ts.commons.FirefoxDriver;
import com.ts.commons.TestCaseUtil;

public class TestCase extends TestCaseUtil{
	
	@Test
	public void test() throws IOException
	{
		FirefoxDriver ffDriver = new FirefoxDriver();
		ffDriver.get("http://testingsoft.com/contact_us");
		new TsHomePage(ffDriver);
		
	}
}

package com.ts.example.IEDriver;

import java.io.IOException;

import org.testng.annotations.Test;

import com.ts.commons.InternetExplorerDriver;
import com.ts.commons.TestCaseUtil;
import com.ts.example.PageGenerator.TsHomePage;

public class TestCase extends TestCaseUtil{
	
	@Test
	public void test() throws IOException
	{
		InternetExplorerDriver driver = InternetExplorerDriver.createInstance();
		driver.get("http://testingsoft.com/");
		new TsHomePage(driver);
	}
}

package com.ts.example.ChromeDriver;

import java.io.IOException;

import org.testng.annotations.Test;

import com.ts.commons.ChromeDriver;
import com.ts.commons.TestCaseUtil;
import com.ts.example.PageGenerator.TsHomePage;

public class TestCase extends TestCaseUtil{
	
	@Test
	public void test() throws IOException
	{
		ChromeDriver driver = ChromeDriver.createInstance();
		driver.get("http://testingsoft.com/");
		new TsHomePage(driver);
	}
}

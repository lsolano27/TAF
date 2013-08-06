package com.videoRecorder.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ts.commons.videoRecorder.Record;

public class googleTranslateTest {	
	WebDriver driver;
	Record rec;
	
	@BeforeMethod
	public void before() throws Exception{				
		rec = new Record("translate Hello World").startRecord();
	}
	
	@Test
	public void test() throws Exception{
		driver = new FirefoxDriver();
		driver.manage().window().maximize();		
		driver.get("http://www.google.co.cr/");		
		
		WebElement translator = driver.findElement(By.xpath(".//*[@id='gb_51']/span[2]"));
		translator.click();
		
		WebElement translate = driver.findElement(By.id("gt-submit"));		
		WebElement wordField = driver.findElement(By.id("source"));
		wordField.sendKeys("Hello world");
		translate.click();
	}

	@AfterClass
	public void tearDown() throws Exception {
		driver.quit();
		rec.stopRecord();
	}
}
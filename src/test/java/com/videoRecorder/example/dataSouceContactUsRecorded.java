package com.videoRecorder.example;

import java.awt.AWTException;
import java.io.IOException;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ts.commons.DataSourceXls;
import com.ts.commons.FirefoxDriver;
import com.ts.commons.videoRecorder.Record;

public class dataSouceContactUsRecorded {	
	FirefoxDriver driver;
	Record rec;
		
	@DataProvider
	public Object[][] data() throws BiffException, IOException {
		return new DataSourceXls("contactUsData.xls").getData();
	}
	
	@BeforeClass
	private void initElements() throws IOException, AWTException{
		rec = new Record("ContactUs Recorded").startRecord();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://www.testingsoft.com/");
		WebElement contactUs = driver.findElement(By.xpath(".//*[@id='menu']/li[2]/a"));
		contactUs.click();	
	}	
	
	@Test(dataProvider = "data")
	public void test(String name, String company, String position, String phone, String mail, 
					 String country, String meansOfContact, String Comments) throws Exception{	
		
		WebElement nameElement = driver.findElement(By.xpath(".//*[@id='ContactForm']/div[1]/input"));
		WebElement companyElement = driver.findElement(By.xpath(".//*[@id='ContactForm']/div[2]/input"));
		WebElement positionElement = driver.findElement(By.xpath(".//*[@id='ContactForm']/div[3]/input"));
		WebElement phoneElement = driver.findElement(By.xpath(".//*[@id='ContactForm']/div[4]/input"));
		WebElement mailElement = driver.findElement(By.xpath(".//*[@id='ContactForm']/div[5]/input"));
		WebElement countryElement = driver.findElement(By.xpath(".//*[@id='ContactForm']/div[6]/select"));
		WebElement meansOfContactElement = driver.findElement(By.id("preferredContact"));
		WebElement CommentsElement = driver.findElement(By.xpath(".//*[@id='ContactForm']/div[8]/textarea"));	
		WebElement reset = driver.findElement(By.xpath(".//*[@id='ContactForm']/div[10]/input[2]"));		
		
		nameElement.sendKeys(name);	
		companyElement.sendKeys(company);	
		positionElement.sendKeys(position);	
		phoneElement.sendKeys(phone);	
		mailElement.sendKeys(mail);	
		countryElement.sendKeys(country);	
		meansOfContactElement.sendKeys(meansOfContact);
		CommentsElement.sendKeys(Comments);
		reset.click();
	}	

	@AfterClass
	public void tearDown() throws Exception {
		driver.quit();		
		rec.stopRecord();
	}
}
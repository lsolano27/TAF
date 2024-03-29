package com.ts.commons;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import com.ts.commons.DownloadFile.DownloadFile;

public class ChromeDriver extends org.openqa.selenium.chrome.ChromeDriver implements  TsDriver{

	public ChromeDriver()
	{
		super();
		manage().timeouts().implicitlyWait(IMPLICT_TIME, IMPLICT_TIME_UNIT );
	}
	
	public ChromeDriver(ChromeDriverService service)
	{
		super(service);
		manage().timeouts().implicitlyWait(IMPLICT_TIME, IMPLICT_TIME_UNIT );
	}
	
	public ChromeDriver(org.openqa.selenium.Capabilities capabilities)
	{
		super(capabilities);
		manage().timeouts().implicitlyWait(IMPLICT_TIME, IMPLICT_TIME_UNIT );
	}
	
	public ChromeDriver(ChromeOptions options)
	{
		super(options);
		manage().timeouts().implicitlyWait(IMPLICT_TIME, IMPLICT_TIME_UNIT );
	}
	
	public ChromeDriver(ChromeDriverService service, ChromeOptions options)
	{
		super(service, options);
		manage().timeouts().implicitlyWait(IMPLICT_TIME, IMPLICT_TIME_UNIT );
	}
	
	
	private static void loadExe()
	{
		String url = "http://tools.testingsoft.com:8888/artifactory/Automation-Files/chromedriver.exe";
		String folder = "src/main/resources";
		String fileName = "chromedriver.exe";
		new DownloadFile(url, folder, fileName);	
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
	}
	
	public static ChromeDriver createInstance()
	{
		loadExe();
		return new ChromeDriver();
	}
	
	public static ChromeDriver createInstance(ChromeDriverService service)
	{
		loadExe();
		return new ChromeDriver(service);
	}
	
	public static ChromeDriver createInstance(org.openqa.selenium.Capabilities capabilities)
	{
		loadExe();
		return new ChromeDriver(capabilities);
	}
	
	public static ChromeDriver createInstance(ChromeOptions options)
	{
		loadExe();
		return new ChromeDriver(options);
	}
	
	public static ChromeDriver createInstance(ChromeDriverService service, ChromeOptions options)
	{
		loadExe();
		return new ChromeDriver(service, options);
	}
	
	
	public void quit()
    {
		 super.quit();
    }
	
	public void get(String domine, String user, String pass)
    {
		 get("https://"+user+":"+pass+"@"+domine);       
    }

	 
	 public void moveToElement(WebElement element)
	 {
		 Actions builder = new Actions(this); 
		 builder.moveToElement(element).build().perform();
	 }

	 public void doubleClick(WebElement element)
	 {
		 Actions builder = new Actions(this); 
		 builder.doubleClick(element).build().perform();
	 }
	 
	 public void clickAndHold(WebElement element)
	 {
		 Actions builder = new Actions(this); 
		 builder.clickAndHold(element).build().perform();
	 }
	  
	 public void dragAndDrop(WebElement source, WebElement target)
	 {
		 Actions builder = new Actions(this); 
		 builder.dragAndDrop(source, target).build().perform();
	 }

	 public void waitToElementBeVisible(final WebElement... element)
	 {
		TestCaseUtil.wait(new Until() {
			
			@Override
			public boolean execute() {
				for(WebElement ele: element )
				{
					boolean elementIsNotVisible = ! ele.isDisplayed();
					if( elementIsNotVisible )
					{
						return false;
					}
				}
				return true;
			}
		});
	 }
	 
	 public void waitToElementBeEnabled(final WebElement... element)
	 {
		 TestCaseUtil.wait(new Until() {
			@Override
			public boolean execute() {
				for(WebElement ele: element )
				{
					boolean elementIsNotVisible = ! ele.isEnabled();
					if( elementIsNotVisible )
					{
						return false;
					}
				}
				return true;
			}
		});
	 }
	 
	 public void waitToElementBeSelected(final WebElement... element)
	 {
		 TestCaseUtil.wait(new Until() {
			@Override
			public boolean execute() {
				for(WebElement ele: element )
				{
					boolean elementIsNotVisible = ! ele.isSelected();
					if( elementIsNotVisible )
					{
						return false;
					}
				}
				return true;
			}
		});
	 }
	 
	 public String getBrowserVersion()
	 {
		 return this.getCapabilities().getVersion();
	 }	 
}

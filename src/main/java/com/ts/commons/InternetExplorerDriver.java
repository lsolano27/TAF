package com.ts.commons;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.interactions.Actions;

import com.ts.commons.DownloadFile.DownloadFile;

public class InternetExplorerDriver extends org.openqa.selenium.ie.InternetExplorerDriver implements  TsDriver{

	public InternetExplorerDriver()
	{
		super();
		manage().timeouts().implicitlyWait(IMPLICT_TIME, IMPLICT_TIME_UNIT );
	}
	
	public InternetExplorerDriver(org.openqa.selenium.Capabilities capabilities) {
		  super (capabilities);
		  manage().timeouts().implicitlyWait(IMPLICT_TIME, IMPLICT_TIME_UNIT );
	}

	  public InternetExplorerDriver(int port) {
		super(port);
		manage().timeouts().implicitlyWait(IMPLICT_TIME, IMPLICT_TIME_UNIT );
	  }
	
	  public InternetExplorerDriver(InternetExplorerDriverService service) {
		super(service);
		manage().timeouts().implicitlyWait(IMPLICT_TIME, IMPLICT_TIME_UNIT );
	  }
	

	private static void loadExe()
	{
		String url = "http://tools.testingsoft.com:8888/artifactory/Automation-Files/IEDriverServer.exe";
		String folder = "src/main/resources";
		String fileName = "IEDriverServer.exe";
		new DownloadFile(url, folder, fileName);	
		System.setProperty("webdriver.ie.driver", "src/main/resources/IEDriverServer.exe");
	}
	
	public static InternetExplorerDriver createInstance() {
		loadExe();
		return new InternetExplorerDriver();
	}

	public static InternetExplorerDriver createInstance(org.openqa.selenium.Capabilities capabilities) {
		  loadExe();
		  return  new InternetExplorerDriver(capabilities);
	}

	  public static InternetExplorerDriver createInstance(int port) {
		  loadExe();
			return new InternetExplorerDriver(port);
	  }
	
	  public static InternetExplorerDriver createInstance(InternetExplorerDriverService service) {
		  loadExe();
			return  new InternetExplorerDriver(service);
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

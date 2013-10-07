package com.ts.commons;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;

import com.ts.commons.maven.MavenConfiguration;


public class FirefoxDriver extends org.openqa.selenium.firefox.FirefoxDriver implements TsDriver{
	
	public FirefoxDriver()
	{
		super();
		afterSetUp();
	
	}
	public FirefoxDriver(FirefoxProfile profile)
	{
		super(profile);
		afterSetUp();
	}
	
	private void afterSetUp()
	{
		manage().timeouts().implicitlyWait(IMPLICT_TIME, IMPLICT_TIME_UNIT );
		
		Validator ffValidations= new Validator() {
			
			@Override
			public void Validate() {
	
				int currentVersion = Integer.parseInt(getBrowserVersion().split("\\.")[0]);
				if(currentVersion < 16)
				{
					throw new RuntimeException("Increase firefox to version 16 or upper, to avoid errors.");
				}
				
				MavenConfiguration mavenConfiguration = new MavenConfiguration();
				String seleniumInPom = mavenConfiguration.getVersion("selenium-java");
				
				if(seleniumInPom == null) 
				{
					throw new RuntimeException("please configure this dependecy in you pom.xml:	<dependency>\n<groupId>org.seleniumhq.selenium</groupId>\n<artifactId>selenium-java</artifactId>\n<version></version>\n</dependency>.");
				}
				
				int seleniumVersion = Integer.parseInt(seleniumInPom.split("\\.")[0]);
				int seleniumRelease = Integer.parseInt(seleniumInPom.split("\\.")[1]);
				if((seleniumVersion <2 || seleniumRelease < 33) && currentVersion == 22 )
				{
					throw new RuntimeException("FiroFox's version 22 needs at least selenium-java 2.33.0.");
				}
				else if((seleniumVersion <2 || seleniumRelease < 34) && currentVersion == 23 )
				{
					throw new RuntimeException("FiroFox's version 23 needs at least selenium-java 2.34.0.");
				}
					
				
			}
		};
		ffValidations.Validate();
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
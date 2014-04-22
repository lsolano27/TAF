package JSExecutor;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ts.commons.TSJavaScriptExecutor;
import com.ts.commons.TestCaseUtil;
import com.ts.example.ITSListener.Page.ExampleBasePage;
import com.ts.example.ITSListener.Page.UI;

public class scripSobreLaUnWebElementSetStyleToBotton extends TestCaseUtil {

	public UI uiInstance;
	ExampleBasePage currentPage;
	
	@BeforeMethod
	public void beforeMethod(){
		uiInstance = new UI(BrowserType.CHROME);
	}
	
	@Test
	public void test1(){
		uiInstance.getDriver().get("http://google.com/");
		WebElement search = uiInstance.getDriver().findElement(By.name("btnK"));		
		TSJavaScriptExecutor.executeScript(uiInstance.getDriver(), "arguments[0].style.border='3px solid red'", search);
	}
	
	@AfterMethod
	public void afterMethod() throws IOException, InterruptedException{		
		uiInstance.getDriver().quit();
	}
}
package JSExecutor;

import java.io.IOException;

import org.openqa.selenium.remote.BrowserType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ts.commons.TSJavaScriptExecutor;
import com.ts.commons.TestCaseUtil;
import com.ts.example.ITSListener.Page.ExampleBasePage;
import com.ts.example.ITSListener.Page.UI;

public class scripSobreLaPaginaCompletaAlert extends TestCaseUtil {

	public UI uiInstance;
	ExampleBasePage currentPage;
	
	@BeforeMethod
	public void beforeMethod(){
		uiInstance = new UI(BrowserType.CHROME);
	}
	
	@Test
	public void test1(){		
		TSJavaScriptExecutor.executeScript(uiInstance.getDriver(), "alert('Esto es un alert de prueba')");		;
		Assert.assertEquals("Esto es un alert de prueba", uiInstance.getDriver().switchTo().alert().getText());
		uiInstance.getDriver().switchTo().alert().accept();
		uiInstance.getDriver().switchTo().defaultContent();
	}
	
	@AfterMethod
	public void afterMethod() throws IOException, InterruptedException{		
		uiInstance.getDriver().quit();
	}
}
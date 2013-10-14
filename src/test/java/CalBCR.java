

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.ts.commons.ChromeDriver;
import com.ts.commons.InternetExplorerDriver;

public class CalBCR {
	 private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
	    driver =  InternetExplorerDriver.createInstance();
	    baseUrl = "http://testingsoft.com/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testUntitled() throws Exception {
	    driver.get(baseUrl + "/");
	    driver.findElement(By.linkText("Home")).click();
	    driver.findElement(By.linkText("What we do")).click();
	    driver.findElement(By.linkText("Contact Us")).click();
	    driver.findElement(By.name("fname")).clear();
	    driver.findElement(By.name("fname")).sendKeys("cristian");
	    driver.findElement(By.name("company")).clear();
	    driver.findElement(By.name("company")).sendKeys("ts");
	    driver.findElement(By.name("position")).clear();
	    driver.findElement(By.name("position")).sendKeys("qa");
	    driver.findElement(By.name("phone")).clear();
	    driver.findElement(By.name("phone")).sendKeys("84545");
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys("a@a.com");
	    new Select(driver.findElement(By.name("country"))).selectByVisibleText("Bahamas");
	    new Select(driver.findElement(By.id("preferredContact"))).selectByVisibleText("E-mail");
	    driver.findElement(By.name("comments")).clear();
	    driver.findElement(By.name("comments")).sendKeys("eafsefsef");
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	}

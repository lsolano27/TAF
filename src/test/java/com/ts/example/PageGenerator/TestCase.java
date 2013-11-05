package com.ts.example.PageGenerator;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ts.commons.FirefoxDriver;
import com.ts.commons.TestCaseUtil;

public class TestCase extends TestCaseUtil{
	
	/**
	 * El POGenerator cuenta con una nueva caracteristica q lo hace mas flexible y usable
	 * la cual es q ahora podemos elegir q elementos queremos generar, en caso de q no queramos generar todos
	 * 
	 * 1.
	 * La ventaja es q los cambios no afectan para nada lo q ya tenemos, por ejemplo el TC queda igual q como 
	 * estaba antes, el cambio sobre POGenerator no lo afecta
	 * 
	 * 2.
	 * El cambio se ve reflejado sobre el nuevo page q queremos generar, ejemplo 
	 * 
	 * 		antes
	 * 				 public TsHomePage(WebDriver driver) 
					{ 
						super(driver); 
					} 	
			
			Ahora podemos agregar la cantidad de elementos q queramos generar
			
					 public TsHomePage(WebDriver driver) 
					{ 
						  super(driver, ElementType.INPUT, ElementType.BUTTON); 
					} 	
	 *Si igual queremos generar todos, nada mas le ponermos super(driver, ElementType.ALL)
	 */
	
	@Test
	public void test() throws IOException
	{
		WebDriver driver = new FirefoxDriver();
		driver.get("http://testingsoft.com/contact_us");
		TsHomePage home = new TsHomePage(driver);	
		
		Assert.assertEquals(home.getPredifinedWebElements()[0].getAttributeBy(),"name", "Incorrect first element BY");
		Assert.assertEquals(home.getPredifinedWebElements()[0].getAttributeValue(),"fname", "Incorrect first element VALUE");		
		Assert.assertEquals(home.getPredifinedWebElements().length,20, "Incorrect num of elements");
	}
}
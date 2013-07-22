package com.ts.commons;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ts.commons.DataSource;
import com.ts.commons.DataSourceXls;

public class DataSourceTest {	
	
	@DataProvider
	public Object[][] data() throws BiffException, IOException{
		return new DataSourceXls("dataTest.xls").getData(0, 2);
	}	
	
	@Test(dataProvider = "data")
	public void test(String name, String lastName, String ID, String address) {
	
		
		System.out.println("*********************************************");
        System.out.println("Name = " + name);
        System.out.println("Last Name = " + lastName);
        System.out.println("ID = " + ID);
        System.out.println("Address = " + address);
        
        Assert.assertNotNull(name);
        Assert.assertNotNull(lastName);
        Assert.assertNotNull(ID);
        Assert.assertNotNull(address);
	}
	
	
}

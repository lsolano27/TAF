package examples;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.ts.commons.dataSourceBuilder.DataSource;

public class dataSourseBuilderTest {	
	
	@DataProvider
	public Object[][] data(){
		return DataSource.getData("dataTest", 0, 2);
	}	
	
	@Test(dataProvider = "data")
	public void test(String name, String lastName, String ID, String address) {
		System.out.println("*********************************************");
        System.out.println("Name = " + name);
        System.out.println("Last Name = " + lastName);
        System.out.println("ID = " + ID);
        System.out.println("Address = " + address);
        System.out.println();
	}
}

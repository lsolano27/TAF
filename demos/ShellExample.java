import org.testng.annotations.Test;

import com.ts.commons.TestCaseUtil;
import com.ts.commons.Validator;


public class ShellExample extends TestCaseUtil{
	
	@Test
	public void test()
	{
		final Device5168 shell = new Device5168();
		  String deviceClass = "ATT"; //ATT or 2Wire
		  
		  using
		   (
		     shell.connect("10.0.0.129", "root", "123456", "#")
		     .setSkipVT100Filter(true)
		     .open()
		     .execute("minicom -m -c on device1")
		     .execute("debugsys --info | grep 'Serial Number' | cut -d ':' -f2")
		     .execute("ifconfig")    
		     .close()
		             
		   )  
		   
		   .check		   
		   (
		    new Validator() {
				
				@Override
				public void Validate() {
					System.out.print("aaaaa   "+shell.getCommandResult().getCommandResult());
					
				}
			}
				   // lanPage.wirelessDefaultSSIDValidator(deviceClass, shell.getCommandResult().getCommandResult())
		   );
	}

}

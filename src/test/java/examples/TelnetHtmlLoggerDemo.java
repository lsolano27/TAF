package examples;
import com.nemo.javaexpect.shell.Shell;
import com.nemo.javaexpect.shell.driver.DefaultShellDriver;
import com.nemo.javaexpect.shell.driver.TelnetDriver;
import com.nemo.javaexpect.shell.logger.HtmlShellLogger;


public class TelnetHtmlLoggerDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TelnetDriver driver= new TelnetDriver("rainmaker.wunderground.com");
		DefaultShellDriver.setShellLogger(new HtmlShellLogger());
		Shell shell = driver.open();
		
		shell.expect("Press Return to continue");
		shell.send("\n");
		shell.expect("enter 3 letter forecast city code--", 10 );
		shell.send("\n");
		shell.expect("Selection:");
		shell.send("\n");
		shell.expect("Selection:", 10);	
		shell.close();
		
		 driver= new TelnetDriver("rainmaker.wunderground.com");
		 driver.setSkipVT100Filter(true);
		 shell = driver.open();
		 shell.expect("Press Return to continue");	
		 shell.close();
	}

}

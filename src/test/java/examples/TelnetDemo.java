package examples;
import com.nemo.javaexpect.shell.Shell;
import com.nemo.javaexpect.shell.driver.TelnetDriver;


public class TelnetDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TelnetDriver driver= new TelnetDriver("135.252.181.222");
		driver.setAutoLogin("bic_guest", "bic_guest", "bic_guest>");
		Shell shell = driver.open();
		shell.execute("ls");
		shell.execute("pwd");
		shell.close();
	}

}

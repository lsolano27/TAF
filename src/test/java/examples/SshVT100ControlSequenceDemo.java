package examples;
import com.nemo.javaexpect.shell.Shell;
import com.nemo.javaexpect.shell.driver.SshDriver;


public class SshVT100ControlSequenceDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SshDriver driver = new SshDriver("135.252.181.79" , "bic_guest", "bic_guest","bic_guest>");
		driver.setSkipVT100Filter(true);
		Shell shell = driver.open();
		shell.execute("ls");
		shell.send("ls");
		shell.expect("bic_guest>" );
		System.out.println(shell.execute("ls").getCommandResult());
		
		driver = new SshDriver("135.252.181.79", "bic_guest", "bic_guest","bic_guest>");
		shell = driver.open();
		shell.execute("ls");
		shell.send("ls");
		shell.expect("bic_guest>" );
		System.out.println(shell.execute("ls").getCommandResult());		
	}

}

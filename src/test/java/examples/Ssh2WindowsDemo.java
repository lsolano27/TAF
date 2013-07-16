package examples;
import com.nemo.javaexpect.shell.Shell;
import com.nemo.javaexpect.shell.driver.ShellDriver;
import com.nemo.javaexpect.shell.driver.SshDriver;


public class Ssh2WindowsDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShellDriver driver = new SshDriver("localhost" , "canhuali", "canhuali","C:\\Documents and Settings\\BvSsh_VirtualUsers>");
		
		Shell shell = driver.open();
		shell.expect(".*Microsoft Windows XP");
		shell.expect("C:\\\\Documents and Settings\\\\BvSsh_VirtualUsers>", 10);
		shell.send("dir");
		shell.expect("C:\\\\Documents and Settings\\\\BvSsh_VirtualUsers>" );
		System.out.println(shell.execute("dir").getCommandResult());
	}

}

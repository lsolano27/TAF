package FTP;
import com.ts.commons.FTPClient.FTP;



public class miFTP extends FTP{
	String host = "127.0.0.1";
	String userName = "Oscar";
	String password = "123";
	String folderName = "MiFolderName";
	
	public FTP miFTPDefaultConnection(){
		return connection(host, userName, password, folderName);
	}
	
	public FTP miFTPDefaultConnectionRoot(){
		return connection(host, userName, password, folderName);
	}
}
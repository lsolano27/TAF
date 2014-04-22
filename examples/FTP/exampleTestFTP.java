package FTP;


import org.testng.annotations.Test;

public class exampleTestFTP {
	
	@Test
	public void test1() throws Exception{		
		new miFTP().miFTPDefaultConnection()
						.and()
						.sendFiles("FTPFiles/abcd.txt");					
	}
	
	@Test
	public void test2() throws Exception{		
		new miFTP().miFTPDefaultConnection()
						.and()
						.sendFilesInFolder("FTPFiles/folderFiles");
	}
}
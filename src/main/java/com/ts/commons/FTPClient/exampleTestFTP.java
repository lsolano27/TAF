package com.ts.commons.FTPClient;

import org.testng.annotations.Test;

public class exampleTestFTP {

	String host = "127.0.0.1";
	String user = "Oscar";
	String pass = "123";
	
	@Test
	public void test1(){		
		new FTP(host, user, pass)
						.and()
						.sendFiles("C:/prueba/abcd.txt", "C:/prueba/folderFiles/FileZilla_Server-0_9_44 [1].exe");					
	}
	
	@Test
	public void test2(){		
		new FTP(host, user, pass)
						.and()
						.sendFilesInFolder("C:/prueba/folderFiles");
	}
}

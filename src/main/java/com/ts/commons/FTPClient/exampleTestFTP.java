package com.ts.commons.FTPClient;

import org.testng.annotations.Test;

public class exampleTestFTP {
	
	@Test
	public void test1(){		
		new miFTP().miFTPDefaultConnection()
						.and()
						.sendFiles("C:/prueba/abcd.txt", "C:/prueba/folderFiles/FileZilla_Server-0_9_44 [1].exe");					
	}
	
	@Test
	public void test2(){		
		new miFTP().miFTPDefaultConnection()
						.and()
						.sendFilesInFolder("C:/prueba/folderFiles");
	}
}
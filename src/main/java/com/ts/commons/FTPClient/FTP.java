package com.ts.commons.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.net.URL;

import org.apache.commons.net.ftp.FTPClient;

import com.ts.commons.Component;

public abstract class FTP implements Component{
	private String host;
	private String user;
	private String pass;
	private String destinyFolder;
	
	protected FTP connection(String host, String userName, String password, String destinyFolder)
	{
		setHost(host);
		setUser(userName);
		setPass(password);
		setDestinyFolder(destinyFolder);
		return this;
	}
	
	public FTP sendFilesInFolder(String folderPath) throws Exception
	{
		URL url = FTP.class.getClassLoader().getResource(folderPath.replace("\\", "/"));	
		File directoryPath = new File(url.getPath());
		boolean existDirectory = directoryPath.exists();
		
		if( ! existDirectory)
		{			
			throw new RuntimeException("Specified folder \"" + folderPath + "\" does not exist");
		}
		
		UploadFiles(directoryPath.listFiles());			
		return this;
	}
	
	public FTP sendFiles(String... filesPaths) throws Exception{

		File[] files = new File[filesPaths.length];		
		
		for (int i = 0; i < filesPaths.length; i++)
		{
			URL url = FTP.class.getClassLoader().getResource(filesPaths[i].replace("\\", "/"));
			files[i] = new File(url.getPath().replace("%5b", "[").replace("%5d", "]").replace("%20", " "));
		}	
		
		UploadFiles(files);		
		return this;
	}
	
	private FTP UploadFiles(File[] files) throws SocketException, IOException {
		FTPClient ftpClient = new FTPClient();
		FileInputStream inputStream = null;	
		int numOfUploadedFiles = 0;
		
		ftpClient.connect(getHost());
		boolean isLogged = ftpClient.login(getUser(), getPass());

		if ( ! isLogged) {
			throw new RuntimeException("FTP could not logged on for this user \"" + getUser() + "\"");
		}		
			
		ftpClient.makeDirectory(getDestinyFolder());		
		
		for (File newFile : files) 
		{								
			newFile.getAbsolutePath();
			
			if (newFile.exists()) 
			{
				inputStream = new FileInputStream(newFile.getAbsolutePath());
				boolean isCurrentFileUploaded = ftpClient.storeFile(getDestinyFolder() + "/" +newFile.getName(), inputStream);
				
				if (isCurrentFileUploaded) 
				{
					numOfUploadedFiles ++;				
				}
			}
		}
			
		if(numOfUploadedFiles != files.length)
		{
			throw new RuntimeException("Not all files were send. " + numOfUploadedFiles + "\" of " + files.length);
		}

		ftpClient.logout();		
		return this;
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getDestinyFolder() {
		return destinyFolder;
	}

	public void setDestinyFolder(String destinyFolder) {
		this.destinyFolder = destinyFolder;
	}

	@Override
	public FTP and() {
		return this;
	}

	@Override
	public FTP then() {
		return this;
	}
}
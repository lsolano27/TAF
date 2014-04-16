package com.ts.commons.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

import com.ts.commons.Component;

public abstract class FTP implements Component{
	private String host;
	private String user;
	private String pass;
	private String[] files;
	
	protected abstract FTP connection(String host, String userName, String password);
	
	public FTP sendFilesInFolder(String folderPath){
		File dir = new File(folderPath);
		
		if(dir.exists()){
			this.files = new String[dir.list().length];
			
			for (int i = 0; i < dir.list().length; i++) {
				this.files[i] = folderPath + "/" + dir.list()[i];
			}	
			
			UploadFiles();
		}else{
			throw new RuntimeException("Specified folder \"" + folderPath + "\" does not exist");
		}		
		return this;
	}
	
	public FTP sendFiles(String... files){
		this.files = files;
		UploadFiles();
		return this;
	}
	
	private FTP UploadFiles() {
		FTPClient ftpClient = new FTPClient();
		FileInputStream inputStream = null;
		String newFolderName = "TSFTPClient uploaded Files";	
		
		try {
			ftpClient.connect(this.host);
			boolean login = ftpClient.login(this.user, this.pass);

			if (login) {
				int numOfUploadedFiles = 0;
				ftpClient.makeDirectory(newFolderName);
				System.out.println("Connection established...");			
				
				for (String file : files) {
					File newFile = new File(file);
					
					if (newFile.exists()) {
						inputStream = new FileInputStream(newFile.getAbsolutePath());
						boolean tempUploaded = ftpClient.storeFile(newFolderName + "/" +newFile.getName(), inputStream);
						
						if (tempUploaded) {
							System.out.println("	" + newFile.getAbsolutePath() + " was uploaded");
							numOfUploadedFiles ++;
						}else{
							System.err.println("	" + newFile.getAbsolutePath() + " was not uploaded");
						}
					}					
				}				
				
				if (numOfUploadedFiles == files.length) {
					System.out.println("	Files uploaded successfully !");
				} else {
					System.out.println("	Error in uploading file !");
				}

				boolean logout = ftpClient.logout();
				if (logout) {
					System.out.println(" Connection close...");
				}
			} else {
				System.out.println(" Connection fail...");
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
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

package com.ts.commons.FTPClient;

public class miFTP extends FTP{
	String host = "127.0.0.1";
	String userName = "Oscar";
	String password = "123";
	
	public FTP miFTPDefaultConnection(){
		connection(host, userName, password);
		return this;
	}
	
	@Override
	protected FTP connection(String host, String userName, String password) {
		super.setHost(host);
		super.setUser(userName);
		super.setPass(password);
		return this;
	}	
}

package com.ts.commons;

public class TelnetLinux extends Telnet{

	@Override
	public Telnet connection(String host, String userName, String password) {
		super.setHost(host);
		super.setUserName(userName);
		super.setPassword(password);
		super.os = OperatingSystem.LINUX_OS;
		return this;
	}
	
	@Override
	public boolean isDisableVT100() {
		return super.isDisableVT100();
	}

	@Override
	public void setDisableVT100(boolean disableVT100) {
		super.setDisableVT100(disableVT100);
	}

}

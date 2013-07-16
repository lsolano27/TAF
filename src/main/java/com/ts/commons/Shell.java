package com.ts.commons;

import com.nemo.javaexpect.shell.CommandResult;
import com.nemo.javaexpect.shell.driver.SshDriver;

public abstract class Shell implements Component {

	private SshDriver sshDriver;
	private com.nemo.javaexpect.shell.Shell shell;
	private CommandResult command;
	
	public Shell connect(String ip, String usuario, String password, String x){
		
		sshDriver=new SshDriver(ip , usuario, password, x);	
		return this;
	}
	
	public Shell setSkipVT100Filter(Boolean b){
		sshDriver.setSkipVT100Filter(b);
		return this;
	}
	
	public Shell open(){
	  shell=  sshDriver.open();
	   return this;
	}
	
	public Shell close() {
		shell.close();
		return this;
	}

	public Shell execute(String arg0) {
		setCommand(shell.execute(arg0));
		return this;
	}

	public Shell execute(String arg0, String arg1) {
		setCommand(shell.execute(arg0, arg1));
		return this;
	}

	public Shell execute(String arg0, int arg1) {
		setCommand(shell.execute(arg0, arg1));
		return this;
	}

	public Shell execute(String arg0, String arg1, int arg2) {
		setCommand(shell.execute(arg0, arg1, arg2));
		return this;
	}

	public Shell expect(String arg0) {
		setCommand(shell.expect(arg0));
		return this;
	}

	public Shell expect(String arg0, int arg1) {
		setCommand(shell.expect(arg0, arg1));
		return this;
	}

	public Shell getLastExitCode() {
		setCommand(shell.getLastExitCode());
		return this;
	}

	public Shell send(String arg0) {
		shell.send(arg0);
		return this;
		
	}

	public CommandResult getCommandResult() {
		return command;
	}

	public void setCommand(CommandResult command) {
		this.command = command;
	}


}

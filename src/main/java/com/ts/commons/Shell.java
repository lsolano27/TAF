package com.ts.commons;

import com.nemo.javaexpect.shell.CommandResult;
import com.nemo.javaexpect.shell.core.DefaultTarget;
import com.nemo.javaexpect.shell.driver.SshDriver;

public abstract class Shell implements Component {

	private SshDriver sshDriver;
	private com.nemo.javaexpect.shell.Shell shell;
	private CommandResult command;
	
	public Shell connect(String host, String loginName, String password, String shellPrompt){
		
		sshDriver=new SshDriver(host , loginName, password, shellPrompt);	
		return this;
	}
	
	public Shell connectWithKey(String host, String loginName, String key, String shellPrompt){
		DefaultTarget target = new DefaultTarget();
		target.setHost(host).
		setAutoLogin(true).
		setLoginName(loginName).
		setLoginPrompt(shellPrompt).
		setKey(key);
		sshDriver=new SshDriver(target);	
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

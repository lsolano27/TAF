package com.nemo.javaexpect.shell.logger;

public class DefaultShellLogger implements ShellLogger {


	public void log(String lineIntestCase, String id, String command, String shellOutput) {
		if (shellOutput!=null)
			System.out.print(shellOutput);
	}

}

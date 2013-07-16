package com.nemo.javaexpect.shell.driver;

import com.nemo.javaexpect.shell.Shell;

/**
 * A driver auto login to the system then provide a shell for user's interaction.
 * @author yunhua
 *
 */
public interface ShellDriver {
	Shell open();
	
	/** remote host name or IP*/
	ShellDriver setHost(String host);
	/** remote port*/
	ShellDriver setPort(int port);
	/** auto input the username and password before user get the shell*/
	ShellDriver setAutoLogin(String loginName, String loginPassword, String shellPrompt);
	/** auto execute su - before  user get the shell*/
	ShellDriver setAutoSU(String suName, String suPassword, String shellPrompt);
	/** set the user default timeout */
	ShellDriver setCommandTimeout(int timeout);
	/** send a "ENTER" key to remote after send the user password*/
	ShellDriver setSendInitialCR(boolean sendInitialCR);
}

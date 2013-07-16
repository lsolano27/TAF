package com.nemo.javaexpect.shell.logger;

/**
 * Shell interaction log interface
 * @author Canhua Li
 *
 */
public interface ShellLogger {
	/**
	 * log the interaction
	 * @param lineIntestCase which line the script is calling the shell operation
	 * @param id	the uniq shell id
	 * @param command current call parameter
	 * @param shellOutput shell output
	 */
	void log(String lineIntestCase, String id,String command, String shellOutput);
}

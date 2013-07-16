package com.nemo.javaexpect.shell;

import com.nemo.javaexpect.shell.logger.ShellLogger;

/**
 * 
 * @author Canhua Li
 *
 */
interface ShellLogable {
	/**provide a uniq ID for every shell*/
	String getShellId();
	
	/** */
	void log(String command, String result);
	
	ShellLogger getLogger();
}

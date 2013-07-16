package com.nemo.javaexpect.shell;

import com.nemo.javaexpect.shell.exception.CommandTimeoutException;

/**
 * @author CanHua Li
 *
 */
public interface Shell {
	/**
	 * close the remote connection
	 */
	public void close();

	/**
	 * 	Send a command in a shell
	 */
	public void send(String command);
	
	/**
	 * 	Send a command in a shell and expect the command has response include a pattern
	 * @param command the command executed in the shell
	 * @param waitForPattern it will wait until a specially pattern matched. 
	 * pattern can refer to regex in {@link java.lang.String#matches(String regex)} 
	 * @see java.lang.String#matches(String regex)
	 * @return 
	 * @throws CommandTimeoutException if CommandTimeout reached
	 */
	public CommandResult execute(String command, String waitForPattern);

	/**
	 * 	Send a command in a shell and expect the command has response include a pattern
	 * @param command the command executed in the shell
	 * @param waitForPattern it will wait until a specially pattern matched. 
	 * pattern can refer to regex in {@link java.lang.String#matches(String regex)} 
	 * @see java.lang.String#matches(String regex)
	 * @param timeout the command timeout timer 
	 * @return 
	 * @throws CommandTimeoutException if CommandTimeout reached
	 */
	public CommandResult execute(String command, String waitForPattern, int timeout);
	
	/**
	 * 	Send a command in a shell and expect the command has response include the login prompt
	 * @param command the command executed in the shell
	 * @return 
	 * @throws CommandTimeoutException if CommandTimeout reached
	 */	
	public CommandResult execute(String command);

	/**
	 * 	Send a command in a shell and expect the command has response include the login prompt
	 * @param command the command executed in the shell
	 * @param timeout the command timeout timer
	 * @return 
	 * @throws CommandTimeoutException if CommandTimeout reached
	 */	
	public CommandResult execute(String command, int timeout);
	
	/**
	 * It command run after {@link #send(String)} and wait for a special pattern. 
	 * 	expect the command has response include a pattern
	 * @param waitForPattern it will wait until a specially pattern matched. 
	 * pattern can refer to regex in {@link java.lang.String#matches(String regex)} 
	 * @see java.lang.String#matches(String regex)
	 * @return 
	 * @throws CommandTimeoutException if CommandTimeout reached
	 */
	public CommandResult expect(String waitForPattern);
	
	/**
	 * It command run after {@link #send(String)} and wait for a special pattern. 
	 * 	expect the command has response include a pattern
	 * @param waitForPattern it will wait until a specially pattern matched. 
	 * pattern can refer to regex in {@link java.lang.String#matches(String regex)} 
	 * @see java.lang.String#matches(String regex)
	 * @param timeout the command timeout timer
	 * @return 
	 * @throws CommandTimeoutException if CommandTimeout reached
	 */
	public CommandResult expect(String waitForPattern, int timeout);	

	/**
	 * return the last command exit code in the remote shell.
	 * It first send "echo XYZ$?ZYX" to remote,
	 * then parse the return value.
	 * @return the last command exit code. 
	 * @throws CommandTimeoutException if CommandTimeout reached
	 */
	public CommandResult getLastExitCode() ;
}

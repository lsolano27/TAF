package com.nemo.javaexpect.shell;

import java.util.regex.Pattern;

public interface CommandResult {

	public String getCommandResult();
	public String getCommand();

	public int getExitCode ();

	/**
	 * assert the result matches with a give pattern. 
	 * @param	expected it's a match pattern.
	 * pattern can refer to regex in {@link java.lang.String#matches(String regex)}
	 * @throws NullPointerException if the given regular expression pattern is {@code null}.
	 * @throw NemoException is nothing matched.	
	 */
	public CommandResult requireText(String expected);
	
	/**
	 * assert the result matches with a give pattern. 
	 * @param pattern the regular expression pattern to match.
     * @throws NullPointerException if the given regular expression pattern is {@code null}.
	 * @throw NemoException is nothing matched.	
	 */
	public CommandResult requireText(Pattern pattern);

	/**
	 * assert the result matches with a give exit code. 
	 * @param expected the int value to match.
	 * @throw NemoException is nothing matched.	
	 */	
	public CommandResult requireExitCode(int expected);
}
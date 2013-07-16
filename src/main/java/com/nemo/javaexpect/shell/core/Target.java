package com.nemo.javaexpect.shell.core;
/**
 * interface for the remote target.
 * it stores all the login information like 
 * the host IP or name, login name and login password...
 * 
 * @author CanHua Li
 *
 */
public interface Target {

	/** Default timeout for the shell when execute a command */
	public final static int DEFAULT_COMMAND_TIMEOUT = 300;
	public final static int INVALID_PORT = -1;

	public boolean isAutoLogin();

	/**
	 * @see #setSUWithLoginShell(boolean)
	 */
	public boolean isSUWithLoginShell();

	/** 
	 * @param suWithLoginShell indicate if make the shell a login shell when execute su
	 * If true, the su command would like:
	 * <code>su - [user]</code>
	 * otherwise
	 * <code>su [user]</code>
	 * */
	public Target setSUWithLoginShell(boolean suWithLoginShell);

	public String getHost();

	/** @param host IP or host name of the target */
	public Target setHost(String host);

	public int getPort();

	public Target setPort(int port);

	public String getLoginName();

	public Target setLoginName(String loginName);

	public String getLoginPassword();

	public Target setLoginPassword(String loginPassword);

	public String getLoginPrompt();

	/**
	 * @param	loginPrompt it's a match pattern.
	 * pattern can refer to regex in {@link java.lang.String#matches(String regex)}
	 */
	public Target setLoginPrompt(String loginPrompt);

	public Boolean getAutoLogin();

	public Target setAutoLogin(Boolean autoLogin);

	public boolean isInitialCR();

	/**
	 * @param	initialCR 
	 * if true, after setup the TCP connection, shell will first send username and password,
	 * then send a \n before the user's interaction
	 * Here is an example need send a 'enter' key before login to the system
	 * <code>
	 * <BR/>telnet localhost 
	 * <BR/> login: test
	 * <BR/> password: ***</code>
	 * <BR/> term=vt100?
	 * <BR/> $
	 * </code>
	 * @return Target itself	
	 */
	public Target setInitialCR(boolean initialCR);

	public String getSuName();

	public Target setSuName(String suName);

	public String getSuPassword();

	public Target setSuPassword(String suPassword);

	public String getSuPrompt();

	/**
	 * @param	suPrompt it's a match pattern.
	 * pattern can refer to regex in {@link java.lang.String#matches(String regex)}
	 * @return Target itself	
	 */
	public Target setSuPrompt(String suPrompt);

	public boolean isAutoSU();

	public Target setAutoSU(boolean autoSU);

	public int getCommandTimeout();

	public Target setCommandTimeout(int commandTimeout);

	public boolean isInvalidPort();

	public Target setShellID(String id);
	public String getShellID();
}
package com.ts.commons;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.net.telnet.TelnetClient;

import com.nemo.javaexpect.shell.CommandResult;
import com.nemo.javaexpect.shell.DefaultCommandResult;
import com.nemo.javaexpect.shell.exception.CommandTimeoutException;
import com.nemo.javaexpect.term.VT100InputStream;


public abstract class Telnet implements Component {

	private TelnetClient telnet;
	private BufferedInputStream input;
	private PrintStream output; // output stream
	private final int waitTime = 1000; // wait time response from reader, in ms
	private String commandResult;
	private String host;
	private String userName;
	private String password;
	private final int EXPECT_SLEEP_TIME_WHEN_NOT_FOUND = 10;
	private byte[] b= new byte[4096];
	private StringBuilder buff = new StringBuilder();
	private final String RETURN_ALL = "ALL";
	public static OperatingSystem os;
	

	public Telnet and() {
		return this;
	}

	public Telnet then() {
		return this;
	}

	public Telnet() {
		
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * Connect to reader
	 * 
	 * @param userName
	 *            : The user name for login
	 * @param password
	 *            : The password for login\
	 * @param host
	 *            : IP address of reader
	 * @return boolean. connect OK or Fail
	 * @throws IOException
	 *             Any problems during connect
	 * @author ThangLe
	 */

	public abstract Telnet connection(String host, String userName, String password);
	
	private boolean connect() {

		telnet = new TelnetClient();
		buff = new StringBuilder();
		try {
/*
			if (telnet != null && telnet.isConnected()) {
				telnet.disconnect();
			}

			if (input != null) {
				input.close();
			}

			if (output != null) {
				
				output.close();
			}
*/
			// Connect to the specified server
			telnet.setConnectTimeout(5000);// Timeout 5s
			telnet.connect(host, 23);

			// Get input and output stream references
			input = new BufferedInputStream(telnet.getInputStream());
			output = new PrintStream(telnet.getOutputStream());

			Thread.sleep(waitTime); // wait for responding from reader
			// Log the user on
			if (readUntil("(([L]|[l])ogin:)", 10) == null)
				return false;
			write(userName);
			Thread.sleep(waitTime); // wait for responding from reader
			if (readUntil("(([P]|[p])assword:)", 10) == null)
				return false;
			write(password);
			Thread.sleep(waitTime); // wait for responding from reader
			// Advance to a prompt
			// readUntil(prompt + " ");

			return true; // connect OK
		} catch (Exception e) {
			e.printStackTrace();
			return false; // connect fail
		}
	}


	private CommandResult readUntil(String waitForPattern, int timeout) throws IOException {

		long start = System.currentTimeMillis();
		long end = timeout * 1000 + start;
		
		String tmp = "";

		Pattern p = Pattern.compile(waitForPattern);
		while (true) {
			InputStream in = this.input;
			try {

				while (in != null && in.available() > 0) {
					int length = in.read(b);
					String temp = filterJunk(new String(b, 0, length));
					buff.append(temp);
				}
			} catch (IOException e) {

			}
			Matcher m = p.matcher(buff);
			if (m.find()) {
				int length = m.end();
				tmp = buff.substring(0, length);
			
				DefaultCommandResult r = new DefaultCommandResult("", tmp);
				buff.delete(0, length);
				return r;
			}
			if (System.currentTimeMillis() > end) {
				tmp = buff.toString();
			
				if(waitForPattern.equals(RETURN_ALL)){
					DefaultCommandResult r = new DefaultCommandResult("", tmp);
					return r;
				}else{				
					throw new CommandTimeoutException("Expect: " + waitForPattern
						+ "\nCurrent buffer: " + tmp);
				}
			} else {
				try {
					Thread.sleep(EXPECT_SLEEP_TIME_WHEN_NOT_FOUND);
				} catch (InterruptedException e) {
				}
			}
		}
		
	}
	
	private String filterJunk(String tmp) throws IOException{
		@SuppressWarnings("resource")
		VT100InputStream v = new VT100InputStream(new ByteArrayInputStream(tmp.getBytes()));
		v.run();
		
		tmp = v.getResult();
		return tmp;
	}

	private void write(String value) {
		try {
			output.println(value);
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Telnet execute(String command) {
		try {
		//	lastCommand = "execute\"(" + command + "\")";
			write(command);
			Thread.sleep(waitTime); // wait for responding from reader
			setCommandResult(readUntil(RETURN_ALL, 10).getCommandResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public Telnet execute(String command, String expect) {
		try {
		//	lastCommand = "execute\"(" + command + "\",\"" + command + "\")";
			write(command);
			Thread.sleep(waitTime); // wait for responding from reader
			setCommandResult(readUntil(expect, 20).getCommandResult());
					//setCommandResult(readUntil(expect));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public Telnet close() {
		try {
			telnet.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public Telnet open() {
		connect();
		return this;
	}

	public String getCommandResult() {
		return commandResult;
	}

	private void setCommandResult(String commandResult) {
		this.commandResult = commandResult;
	}

}

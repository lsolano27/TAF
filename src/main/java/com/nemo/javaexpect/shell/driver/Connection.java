package com.nemo.javaexpect.shell.driver;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * A remote connection, it contains an InputStream and OutputStream
 * @author yunhua
 *
 */
public interface Connection {
	public void close();
	public OutputStream getOutputStream();
	public InputStream getInputStream();	
	boolean isActive();
}

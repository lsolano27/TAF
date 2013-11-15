package com.nemo.javaexpect.shell;

import java.util.regex.Pattern;

import org.junit.Test;

import com.nemo.javaexpect.shell.exception.NemoException;

public class DefaultCommandResultTest {

	@Test
	public void testRequireTextString() {
		DefaultCommandResult r = new DefaultCommandResult("test", "hello 123 test");
		r.requireText("123");
		r.requireText("test");
	}

	@Test(expected=NullPointerException.class)
	public void testRequireTextStringNullPoint() {
		DefaultCommandResult r = new DefaultCommandResult("test", "hello 123 test");
		r.requireText((String)null);
	}

	@Test(expected=NullPointerException.class)
	public void testRequirePatternStringNullPoint() {
		DefaultCommandResult r = new DefaultCommandResult("test", "hello 123 test");
		r.requireText((Pattern)null);
	}
	
	@Test
	public void testRequireTextPattern() {
		DefaultCommandResult r = new DefaultCommandResult("test", "hello 123 test");
		Pattern p = Pattern.compile("123");
		r.requireText(p);
	}

	@Test
	public void testRequireExitCode() {
		DefaultCommandResult r = new DefaultCommandResult("test", 2);
		r.requireExitCode(2);
	}
	@Test(expected=NemoException.class)
	public void testRequireExitCodeException() {
		DefaultCommandResult r = new DefaultCommandResult("test", 2);
		r.requireExitCode(3);
	}

}

package com.nemo.javaexpect.shell;

import org.junit.Test;

import com.nemo.javaexpect.shell.core.DefaultTarget;
import com.nemo.javaexpect.shell.core.Target;

public class RemoteShellTest {
	@Test
	public void testRemoteShellFromTarget() {
//		Shell fe = new SshShell();
		Target target= new DefaultTarget();

//		fe.open("135.252.181.79", 22);
//		fe.execute("ls");
	}
	/*
	@Test
	public void test() {
		Shell fe = new Shell();
		RemoteMachine shellClientInfo= new RemoteMachine();
		fe.setRemoteShellTarget(shellClientInfo);
		
		fe.setPrompt(">");
		fe.setInitialCR(null);

		fe.setCommandTimeout(60);
		fe.getCommandTimeout();
		fe.open("135.252.181.79", 23);
		fe.open();
		fe.su();
		fe.exitSu();
		fe.execute("ls", ">");
		fe.execute("ls");
		fe.send("ls");
		fe.expect(">");
		
	}
*/
}

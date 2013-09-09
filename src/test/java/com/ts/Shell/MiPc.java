package com.ts.Shell;

import org.testng.Assert;

import com.ts.commons.Component;
import com.ts.commons.Shell;

public class MiPc extends Shell{
	
	private boolean pingIsOk = false;
	
	public MiPc()
	{
		
	}
	
	public MiPc validateExpectedOutPut() {
		Assert.assertEquals(pingIsOk, true, "ping comand was failed");
		return this;
	}

	public MiPc connect(String ip, String user, String password)
	{
		super.connect(ip, user, password, user);
		setSkipVT100Filter(true);
		open();
		return this;
	}
	
	public MiPc sendPingCommandToGateWay()
	{
		execute("ping 10.0.0.1", "Approximate");
		pingIsOk = true;
		return this;
	}
	
	public MiPc and() {
		return this;
	}

	public MiPc then() {
		return this;
	}


}

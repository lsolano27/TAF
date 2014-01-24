package com.jssc;

import org.junit.Test;

import com.ts.commons.Telnet;
import com.ts.commons.TelnetWindows;

public class TestTelnet{

	@Test
	public void testTelnet(){
		
		String wirelessName = "Test";
		String wifiInterface = "eth1";
		
		Telnet telnet = new TelnetWindows();
		telnet.connection("10.0.0.91", "TesterUser", "123456");
		telnet.open();
	//	telnet.execute("iwlist " + wifiInterface + " scan | grep " + wirelessName, "(SSID:" + '"' + wirelessName + '"'+ ")");
		
		telnet.execute("ping 10.0.0.1", 10);
		//telnet.execute("netsh interface set interface \"Local Area Connection\" disable");
		System.out.println(telnet.getCommandResult());
		telnet.close();		
	}
}

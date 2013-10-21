package com.jssc;

import jssc.SerialPortException;

import org.junit.Test;

import com.ts.commons.Serial;

public class TestSerial{
	
	@Test
	public void testSerial() throws SerialPortException, InterruptedException{
		Serial s = new Serial("COM3");
		s.open();		
		s.setConnectionParameters(115200, 8, 1, 0);
		s.applyConnectionParameters();
		s.execute("ifconfig", 5000);
		System.out.println(s.getCommandResult());
		s.execute("debugsys --info");
		System.out.println(s.getCommandResult());
		s.close();
		
	}
}

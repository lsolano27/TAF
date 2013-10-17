package com.ts.commons;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class Serial implements Component{

	private SerialPort serialPort;
	private StringBuilder result;
	private int breakTime = 1000;
	private int baudRate;
	private int dataBits;
	private int stopBits; 
	private int parity;

	public Serial and() {
		return this;
	}

	public Serial then() {
		return this;
	}
	
	public Serial(String port){
		serialPort = new SerialPort(port);
		result = new StringBuilder();
	}
	
	public Serial setConnectionParameters(int baudRate, int dataBits,
			int stopBits, int parity){
		this.baudRate = baudRate;
		this.dataBits = dataBits;
		this.stopBits = stopBits;
		this.parity = parity;
		
		return this;
	}


	public Serial applyConnectionParameters(){		
		try {
			serialPort.setParams(baudRate, dataBits, stopBits, parity);
			int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR + SerialPort.MASK_BREAK + SerialPort.MASK_ERR + SerialPort.MASK_RING + SerialPort.MASK_RLSD + SerialPort.MASK_RXFLAG + SerialPort.MASK_TXEMPTY;//Prepare mask
	        serialPort.setEventsMask(mask);
			serialPort.addEventListener(new SerialEvent());
			serialPort.setDTR(false);
			serialPort.setRTS(false);
			
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
		
		return this;
	}

	/**
	 * If you execute more than one command continuously, please use the method execute(String command, in break Time)
	 * @param command
	 * @return
	 * @throws SerialPortException
	 * @throws InterruptedException
	 */
	public Serial execute(String command) throws SerialPortException, InterruptedException {
		return execute(command, breakTime);
	}
	
	public Serial execute(String command, int breakTime) throws SerialPortException, InterruptedException {
		
		serialPort.writeString(command);
		serialPort.writeString(String.valueOf('\r'));
		serialPort.sendBreak(breakTime);
		return this;
	}
	
	
	public Serial open() throws SerialPortException{
		serialPort.openPort();
		return this;
	}
	
	public Serial close() throws SerialPortException{
		serialPort.closePort();
		return this;
	}

	public String getCommandResult() throws SerialPortException, InterruptedException {
		return result.toString();
	}

	class SerialEvent implements SerialPortEventListener {

		public void serialEvent(SerialPortEvent event) {

			if (event.isRXCHAR()) {// If data is available
				if (event.getEventValue() > 1) {// Check bytes count in the
												// input buffer
					// Read data
					try {
						byte buffer[] = serialPort.readBytes();
						result.append(new String(buffer));
					} catch (SerialPortException ex) {
						System.out.println(ex);
					}
				}
			} else if (event.isCTS()) {// If CTS line has changed state
				if (event.getEventValue() == 1) {// If line is ON
					
				} else {
					
				}
			} else if (event.isDSR()) {// /If DSR line has changed state
				if (event.getEventValue() == 1) {// If line is ON
				
				} else {
					
				}
			} else if (event.isERR()) {
				throw new RuntimeException("" + event.getEventValue());
			} else if (event.isRING()) {
				
			} else if (event.isTXEMPTY()) {
				
			}
		}
	}
	
}

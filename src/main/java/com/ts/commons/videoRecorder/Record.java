package com.ts.commons.videoRecorder;

import java.awt.AWTException;
import java.io.IOException;

public class Record extends RecordExt{	
	public Record(String fileName) throws IOException, AWTException {		
		super(fileName);		
	}

	public Record startRecord() throws IOException{
		start();
		return this;
	}

	public void stopRecord() throws IOException{
		stop();
	}
}
package com.ts.commons.TSRunReportXls;

import java.io.File;
import java.util.ArrayList;

class XLS {
	protected String xlsName;
	protected ArrayList<String> columsHeader;	
	protected String directoryName;
	String project;
	
	public XLS(String xlsName){
		this.setXlsName(xlsName);
		File root = new File("src");
		String diskLocation = root.getAbsolutePath().replace("\\", "/");
		int reference = diskLocation.lastIndexOf("/src");
		String[] allSplitedPath = diskLocation.substring(0, reference).split("/");
		project = "Project Name: " + allSplitedPath[allSplitedPath.length - 1];			
	}
	
	public ArrayList<String> getColumsHeader() {
		return columsHeader;
	}

	public void setColumsHeader(ArrayList<String> columsHeader) {
		this.columsHeader = columsHeader;
	}
	
	public String getXlsName() {
		return xlsName;
	}

	public String getDirectoryName() {
		return directoryName;
	}

	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	public void setXlsName(String xlsName) {
		this.xlsName = xlsName;
	}		
}
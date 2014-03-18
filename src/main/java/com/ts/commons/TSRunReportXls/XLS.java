package com.ts.commons.TSRunReportXls;

import java.util.ArrayList;

class XLS {
	protected String xlsName;
	protected ArrayList<String> columsHeader;	
	protected String directoryName;
	String project;
	
	public XLS(String xlsName){
		this.setXlsName(xlsName);
		String diskLocation = this.getClass().getResource("").getPath();
		int reference = diskLocation.lastIndexOf("/target");
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
package com.ts.commons;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import jxl.Workbook;
import jxl.read.biff.BiffException;

public abstract class DataSource {
	
	private File file;
	protected Workbook book; 
	
	public DataSource(String path) throws BiffException, IOException
	{
		URL url = DataSource.class.getClassLoader().getResource(path);			
		if(url == null){
			throw new RuntimeException("Specified file \"" + path + "\" does not exist");
		}else{
			file = new File(url.getPath());
			openBook(file);
		}	
	}
	
	protected void openBook(File file) throws BiffException, IOException{
		book = Workbook.getWorkbook(file);
	}
	
	protected void closeBook(){
		if (book != null) {
			book.close();
		}
	}
	
	public abstract Object[][] getData()  throws Exception;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}	
}
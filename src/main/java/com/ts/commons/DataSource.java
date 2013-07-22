package com.ts.commons;

import java.io.File;
import java.net.URL;

public abstract class DataSource {
	
	private File file;
	
	public DataSource(String path)
	{
		URL url = DataSource.class.getClassLoader().getResource(path);
		file = new File(url.getPath());
		if( ! file.exists() )
		{
			new RuntimeException("Specified file \"" + file.getAbsolutePath() + "\" does not exist");
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

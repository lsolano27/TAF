package com.ts.commons.DownloadFile;

/**
 * This feature will Download a file from specific URL.
 * If file exist, it will not be downloaded again.	
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadFile {
	
	File file;
	
	public DownloadFile(String url, String folder, String fileName)
	{
		File directoryToDownload = new File(folder);
		File fileToDownload = new File(folder + "/" + fileName);
		
		if ( ! directoryToDownload.exists())
		{
			if ( ! directoryToDownload.mkdir())
			{
				throw new RuntimeException("Directory " + directoryToDownload.getAbsolutePath() + " was not created");
			}
		}
		
		if( ! fileToDownload.exists()){
			urlConnect(url, fileToDownload);			
		}		
		file = fileToDownload;
	}
	
	public double getFileSizeInKB()
	{
		return (file.length() / 1024);
	}
	
	private boolean urlConnect(String url, File file)
	{
		try 
		{
			URLConnection conn = new URL(url).openConnection();
			conn.connect();
			System.err.println("Please wait until system download file: " + file.getName() + " ........");
			openStream(conn, file);
			System.err.println("Thanks, file was downloaded");
			return true;
		} 
		catch (Exception e) 
		{
			return false;
		}		
	}
	
	private void openStream(URLConnection conn, File file) throws IOException
	{
		int b = 0;
		InputStream in = conn.getInputStream();
		OutputStream out = new FileOutputStream(file);
		
		while (b != -1) 
		{
		  b = in.read();
		  if (b != -1)
		    out.write(b);
		}
		
		out.close();
		in.close();
	}
}
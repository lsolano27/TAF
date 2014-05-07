package com.ts.TAFTools.SeleniumPOMVersion;

import static com.jayway.restassured.RestAssured.given;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.jayway.restassured.internal.path.xml.NodeChildrenImpl;
import com.jayway.restassured.response.Response;

public class SeleniumPOMVersion 
{		
	public static void main(String [] arg) throws Exception 
	 {		
		updateSeleniumVersion();		
	 }
	
	public static void updateSeleniumVersion() throws Exception 
	{
		System.out.println("Selenium version will be updated.....");
		String version = getSeleniumVersion();
		File pom = new File("");
		pom = new File(pom.getAbsolutePath() + "/pom.xml");
		updateSeleniumVersionInPOM(pom, version);
		System.out.println("Selenium version updated to version " + version);
	}
	
	private static String getSeleniumVersion()
	{
		String seleniumVersion = null;
		Response resp = given().get("http://selenium-release.storage.googleapis.com/");	
		NodeChildrenImpl nodes =  resp.xmlPath().get("ListBucketResult.Contents.Key");
		
		for (String node : nodes) 
		{
			if(node.contains("selenium"))
			{
				seleniumVersion = node.split("/")[0] + ".0";
			}			
		}				
		return seleniumVersion ;
	}	
	
	private static void updateSeleniumVersionInPOM(File pom, String version) throws Exception
	{
		String content = getFileContent(pom, version);
		replacePOM(pom, content);
	}
	
	private static String getFileContent(File pom, String version) throws Exception 
	{
		boolean found = false;
		int index = 1;
		StringBuilder content = new StringBuilder();
		BufferedReader in = new BufferedReader(new FileReader(pom));
		
		try 
		{
			String line = null;		
			
			while ((line = in.readLine()) != null) 
			{
				if (found) 
				{
					index ++;
					if(index == 3)
					{
						line = "\t\t\t<version>"+ version +"</version>";						
					}					
				}
				
				if (line.contains("org.seleniumhq.selenium")) 
				{
					found = true;
				}
				
				content.append(line);
				content.append(System.getProperty("line.separator"));				
			}			
		} 
		finally 
		{
			in.close();
		}
		return content.toString();
	}
	
	private static void replacePOM(File pom, String content) throws Exception 
	{
		if (pom == null) 
		{
			throw new IllegalArgumentException("File must not be null");
		}	
		
		FileWriter fileWritter = new FileWriter(pom.getName());
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        bufferWritter.write(content);
        bufferWritter.close();		
	}
}
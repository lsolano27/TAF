package com.ts.commons.maven;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MavenConfiguration {

	private ArrayList<Dependency> dependencies = new ArrayList<Dependency>();
	
	public String getVersion(String artifactId)
	{
		for(Dependency dependency: dependencies)
		{
			if(dependency.getArtifactId().equals(artifactId))
			{
				return dependency.getVersion();
			}
		}
		return null;
	}
	
	public MavenConfiguration() {
		NodeList nList = null;
		try {
			File fXmlFile = new File("pom.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);	
			nList = doc.getElementsByTagName("dependency");
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);		 
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
				Element eElement = (Element) nNode;
				NodeList subNodes = eElement.getChildNodes();
				
				String groupId="";
				String artifactId="";
				String version="";
				
				for (int temp2 = 0; temp2 < subNodes.getLength(); temp2++) {
					Node nNode2 = subNodes.item(temp2);
					if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
						
						if(nNode2.getNodeName().equals("groupId"))
						{
							groupId = nNode2.getTextContent();
						}
						else if(nNode2.getNodeName().equals("artifactId"))
						{
							artifactId = nNode2.getTextContent();
						}
						else if(nNode2.getNodeName().equals("version"))
						{
							version = nNode2.getTextContent();
						}
					}
				}
				dependencies.add(new Dependency(groupId,artifactId,version));
			}
		}

	}

}

class Dependency{
	
	private String groupId="";
	private String artifactId="";
	private String version="";
	
	public Dependency(String groupId, String artifactId, String version) {
		super();
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = version;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getArtifactId() {
		return artifactId;
	}
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	
	
}

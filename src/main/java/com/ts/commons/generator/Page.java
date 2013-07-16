package com.ts.commons.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import com.ts.commons.Component;

public abstract class Page extends com.ts.commons.Page implements Component{	

	private String pageName;
	private ArrayList<String> lines = new ArrayList<String>();
	private ArrayList<PoWebElement> arrayInLines = new ArrayList<PoWebElement>();
	private ArrayList<String> webElementsDeclarations = new ArrayList<String>();
	private PoJavaScriptExecutor poJavaScriptExecuto;
	private PoWebElement[] preDefinedWebElements;
	private File outputFile = null;
	private boolean firstWrite= false;	
	private BufferedWriter bw;
	private PrintWriter wr;
	
	public PoWebElement[] getPredifinedWebElements()
	{
		return null;
	}
	
	
	public Page(WebDriver driver)
	{
		poJavaScriptExecuto = new PoJavaScriptExecutor(driver);
		preDefinedWebElements = getPredifinedWebElements();
		if( preDefinedWebElements == null)
		{
			generatePO(driver);	
		}
	}
	
	private Page generatePO (WebDriver driver){
		String className = this.getClass().getName();
		className = className.replaceAll("\\.", "/");
		className = className+".java";
		
		String diskLocation = this.getClass().getResource("").getPath();
		int reference = diskLocation.lastIndexOf("/target");
		diskLocation = diskLocation.substring(0, reference);
		
		diskLocation = diskLocation+"/src/test/java/"+className;
		
		boolean classIsNeverGenerated = preDefinedWebElements == null;
		if(classIsNeverGenerated)
		{
			new PoGenetaror(driver)
			.setNewPageName(diskLocation)
			.generatePageObject(this);	
			
		}		
	
		return this;			
	}
	
	protected Page generateFile(String pageName){	
		this.pageName = pageName;
		
		return
		includeImports()
					.then()
					.includeClassDeclaration(pageName)
					.then()
					.generateContructor()
					.then()
					.generateArrayInPageObject(arrayInLines)
					.then()
					.includeWebElements()
					.and()
					.includeOverrides(pageName)
					.createFile();
	}
	
	private Page createFile(){
		mkdFile(pageName)
				.printElementsDeclarations(lines)
				.CloseFile();
		poJavaScriptExecuto.succesAlert();
		return this;
	}
	
	protected Page storageWebElements(ArrayList<PoWebElement> list, int numOfFrames){
		int index = 0;
		arrayInLines.addAll(list);
				
		if(numOfFrames > 0){
			webElementsDeclarations.add("");
			webElementsDeclarations.add("	//*******************");
			webElementsDeclarations.add("	//*	FRAME OR IFRAME *");
			webElementsDeclarations.add("	//*******************");
			webElementsDeclarations.add("");
		}	
		
		for (PoWebElement webElement: list) {				
				webElementsDeclarations.add("	@FindBy(" + webElement.attributeBy + " = \"" + webElement.attributeValue + "\")");
				
				if( ! webElement.attributeBy.equals("xpath")){
					webElementsDeclarations.add("	WebElement " + webElement.attributeValue.replace(".", "").replace("-", "_") + ";");
				}else{
					index ++;
					webElementsDeclarations.add("	WebElement " + "VariableNameToDefine" + index + ";");
				}				
				webElementsDeclarations.add("");
		}		
		return this;
	}
	
	private Page generateContructor(){
		
		String construtor ="\t public "+this.getClass().getSimpleName()+"(WebDriver driver) \n\t { \n\t\t super(driver); \n\t} \n";
		lines.add(construtor);
		
		return this;		 
	}
	
	private Page generateArrayInPageObject(ArrayList<PoWebElement> list){
		String generatedArray ="\t/* \n\t* Don't delete or change this method, or the object will be generated again.\n\t*/ \n";
		generatedArray +="\tpublic PoWebElement[] getPredifinedWebElements()\n";
		generatedArray += "\t{\n";
		generatedArray += "\t\treturn new PoWebElement[] {";
		for (PoWebElement element : list) {
			generatedArray += "new PoWebElement(\"" + element.attributeBy + "\",\"" + element.attributeValue + "\")";
			
			if(list.indexOf(element) != (list.size() - 1)){
				generatedArray += ", ";
			}
		}
		generatedArray += "};\n";
		generatedArray += "\t}\n";
		lines.add(generatedArray);
		return this;		 
	}
	
	private Page includeWebElements(){
		lines.addAll(webElementsDeclarations);
		return this;
	}
	
	private Page includeImports(){
		lines.add("package "+this.getClass().getPackage().getName()+"; ");
		lines.add("");
		lines.add("import org.openqa.selenium.WebElement;");
		lines.add("import org.openqa.selenium.support.FindBy;");
		lines.add("import com.ts.commons.generator.Page;");
		lines.add("import com.ts.commons.generator.PoWebElement;");
		lines.add("import org.openqa.selenium.WebDriver;");
		lines.add("");
		return this;
	}
	
	private Page includeClassDeclaration(String pageName){
		lines.add("public class "+ this.getClass().getSimpleName() +" extends Page {");
		lines.add("");
		return this;
	}
	
	private Page includeOverrides(String pageName){
		lines.add("	public "+this.getClass().getSimpleName()+" and() {");
		lines.add("		return this;");
		lines.add("	}");
		lines.add("");
		lines.add("	public "+this.getClass().getSimpleName()+" then() {");
		lines.add("		return this;");
		lines.add("	}");
		lines.add("}");
		return this;
	}
	
	public Page and(){
		return this;
	}

	public Page then(){
		return this;
	}

	public PoWebElement[] getPreDefinedWebElements() {
		return preDefinedWebElements;
	}

	public void setPreDefinedWebElements(PoWebElement[] preDefinedWebElements) {
		this.preDefinedWebElements = preDefinedWebElements;
	}	
	
	protected Page mkdFile(String fileName){
		try {			
			FileWriter w;      
	
			outputFile = new File(fileName);			
				w = new FileWriter(outputFile);						
				bw = new BufferedWriter(w);	
				wr = new PrintWriter(bw);	
			return this;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}						
	}	
	
	protected Page printElementsDeclarations(ArrayList<String> lines){
		try {
			for (String line : lines) {			
				write(line).bw.newLine();			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return this;
	}
	
	protected Page CloseFile(){
		try {
			wr.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}			
		return this;
	}
	
	private Page write(String text){
		if (firstWrite){
			wr.write(text); 
			firstWrite =false;
		}else{
			wr.append(text); 			
		}		
		return this;
	}	
}
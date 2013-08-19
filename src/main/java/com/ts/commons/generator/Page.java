package com.ts.commons.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	private final String defaultGeneratedVarName = "'Sorry, please define this variable name';";
	
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
	
	private Page generatePO (WebDriver driver) {
		String className = this.getClass().getName();
		className = className.replaceAll("\\.", "/");
		className = className+".java";
		
		String diskLocation = this.getClass().getResource("").getPath();
		int reference = diskLocation.lastIndexOf("/target");
		diskLocation = diskLocation.substring(0, reference);
		
		diskLocation = diskLocation+"\\src\\test\\java\\"+className;
		
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
					.includeClassDeclaration()
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
	
	protected Page storageWebElements(WebDriver driver, ArrayList<PoWebElement> list, int numOfFrames){		
		arrayInLines.addAll(list);
				
		if(numOfFrames > 0){
			webElementsDeclarations.add("");
			webElementsDeclarations.add("	//*******************");
			webElementsDeclarations.add("	//*	FRAME OR IFRAME *");
			webElementsDeclarations.add("	//*******************");
			webElementsDeclarations.add("");
		}
		
		for (PoWebElement webElement: list) {
			boolean hasXpath = false;
			WebElement element = null;
			webElementsDeclarations.add("	@FindBy(" + webElement.attributeBy + " = \"" + webElement.attributeValue + "\")");
			
			if(webElement.attributeBy.equals("xpath")){
				hasXpath = true;
				element = driver.findElement(By.xpath(webElement.attributeValue));
			}		
			
			if( ! hasXpath){
				webElementsDeclarations.add("	WebElement " + generateVarName(webElement.attributeTag + "." + webElement.attributeValue));
			}else if(hasXpath){				
				if( ! element.getText().trim().equals("")){
					webElementsDeclarations.add("	WebElement " + generateVarName(webElement.attributeTag + "." + element.getText()));
				}else if(! element.getAttribute("value").trim().equals("")){
					webElementsDeclarations.add("	WebElement " + generateVarName(webElement.attributeTag + "." + element.getAttribute("value")));
				}else{
					webElementsDeclarations.add("	WebElement " + defaultGeneratedVarName);
				}
			}				
			webElementsDeclarations.add("");
		}		
		return this;
	}
	
	private String generateVarName(String gottenValue){
		String generatedName = removeAcentAndLongNumber(gottenValue);
		generatedName = removeSpecialCharacters(generatedName);
		return generatedName + ";";		
	}	
	
	private String removeAcentAndLongNumber(String input){
		Pattern pattern;
		String normalized = Normalizer.normalize(input, Form.NFD);
		String expression = "\\P{ASCII}";
		
		if(removeLongNumber(input)){
			expression = expression.replace("}", "|\\d");
		}		
		
		pattern = Pattern.compile(expression);
		return pattern.matcher(normalized).replaceAll("");
	}
	
	private boolean removeLongNumber(String input){
		int consecutiveNumberCount = 0;
	       
		for (char c : input.toCharArray()) {
			if(consecutiveNumberCount > 3){
				return true;
			}else if(consecutiveNumberCount <= 3){
				if (c >= '0' && c <= '9'){
					consecutiveNumberCount ++;
				}else{
					consecutiveNumberCount = 0;
				}
			}							
		}
		
		return false;		
	}
	
	private String removeSpecialCharacters(String input)
	{		 
		boolean isNextLetterInUpperCase = false;
        StringBuilder newValue = new StringBuilder();
        
        for (char c : input.toCharArray())
        {
        	if(c == ' ' || c == '.' || c == '_'){
        		isNextLetterInUpperCase = true;
        	}
        	
        	if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
            {
            	if(isNextLetterInUpperCase){
            		c = Character.toUpperCase(c);
            		isNextLetterInUpperCase = false;
            	}            	
            	newValue.append(c);
            }
        }
        
        return newValue.toString().substring(0, 1).toLowerCase() + newValue.substring(1);     
	}
	
	private Page generateContructor(){
		
		String construtor ="\t public "+this.getClass().getSimpleName()+"(WebDriver driver) \n\t{ \n\t\t super(driver); \n\t} \n";
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
		lines.addAll(fixDuplicated(webElementsDeclarations));
		return this;
	}
	
	private ArrayList<String> fixDuplicated(ArrayList<String> declarations){
		for (int i = 0; i < declarations.size() - 1; i++) {
			if((declarations.get(i).contains("WebElement")) && ( ! declarations.get(i).contains(defaultGeneratedVarName))){			
				int index = 1;
				for (int j = (i + 1); j < declarations.size() - 1; j++) {
					String tmpDeclaration = declarations.get(j);
					if(tmpDeclaration.equals(declarations.get(i))){
						declarations.set(i, tmpDeclaration.replace(";", index + ";"));
						declarations.set(j, tmpDeclaration.replace(";", (index + 1) + ";"));
					}
				}
			}
		}			
		return declarations;		
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
	
	private Page includeClassDeclaration(){
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
			//File directorio = new File(fileName);
			
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
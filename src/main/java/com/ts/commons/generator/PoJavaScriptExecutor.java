package com.ts.commons.generator;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PoJavaScriptExecutor {
	@SuppressWarnings("unused")
	private JavascriptExecutor js;
	private WebDriver driver;
	
	public PoJavaScriptExecutor (WebDriver driver)
	{
		this.driver = driver;
	}
	
	@SuppressWarnings("unchecked")
	public Integer getNumOfElements(String javaScript, String type){
		int count = 0;
		boolean isInput = false;
		js = (JavascriptExecutor)driver;		
		List<WebElement> elements =  (List<WebElement>) ((JavascriptExecutor)driver).executeScript(javaScript);
		
		if(elements.size() > 0){
			if(elements.get(0).getTagName().equals("input")){
				isInput = true;
			}
			
			if(isInput){
				for (WebElement webElement : elements) {
					if(webElement.getAttribute("type").equals(type)){
						count++;
					}			
				}				
			}else{
				return elements.size();
			}
		}						
		return count;
	}
	
	public String getElementByXPath(WebElement element){
		String javaScript = "gPt=function(c){" +
								"if(c.id!==''){" +
									"return'.//*[@id=\"'+c.id+'\"]'" +
								"}" +
								"if(c===document.body){" +
									"return'.//'+c.tagName.toLowerCase()" +
								"}" +
								"var a=0;" +
								"var e=c.parentNode.childNodes;" +
								"for(var b=0;b<e.length;b++){" +
									"var d=e[b];" +
									"if(d===c){" +
										"return gPt(c.parentNode)+'/'+c.tagName.toLowerCase()+'['+(a+1)+']'" +
									"}" +
									"if(d.nodeType===1&&d.tagName===c.tagName){" +
										"a++" +
									"}" +
								"}" +
							"};" +
							"return gPt(arguments[0]);";			
		
		return (String)((JavascriptExecutor)driver).executeScript(javaScript, element);
	}

	public void succesAlert(){
		String script = "alert(\"Page was generated!!\");";
		((JavascriptExecutor)driver).executeScript(script);
	}
}
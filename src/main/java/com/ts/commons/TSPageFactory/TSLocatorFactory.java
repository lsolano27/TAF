package com.ts.commons.TSPageFactory;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public class TSLocatorFactory implements ElementLocatorFactory{
	 private final SearchContext searchContext;

	  public TSLocatorFactory(SearchContext searchContext) {
	    this.searchContext = searchContext;
	  }

	  public ElementLocator createLocator(Field field) {
	    return new TSElementLocator(searchContext, field);
	  }
}

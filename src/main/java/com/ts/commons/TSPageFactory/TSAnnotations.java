/*
Copyright 2007-2009 Selenium committers

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.ts.commons.TSPageFactory;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.pagefactory.ByChained;

public class TSAnnotations extends Annotations{

	private Field field;

	public TSAnnotations(Field field) {
		super(field);
		this.field = field;
	}

	public By buildBy() {
		assertValidAnnotations();

		By ans = null;

		FindBys findBys = field.getAnnotation(FindBys.class);
		if (findBys != null) {
			ans = buildByFromFindBys(findBys);
		}

		FindAll findAll = field.getAnnotation(FindAll.class);
		if (ans == null && findAll != null) {
			ans = buildBysFromFindByOneOf(findAll);
		}

		FindBy findBy = field.getAnnotation(FindBy.class);
		if (ans == null && findBy != null) {
			ans = buildByFromFindBy(findBy);
		}

		if (ans == null) {
			ans = (By) buildByFromDefault();
		}

		if (ans == null) {
			throw new IllegalArgumentException(
					"Cannot determine how to locate element " + field);
		}

		return ans;
	}
	
	protected By buildByFromFindBys(FindBys findBys) {
		assertValidFindBys(findBys);

		FindBy[] findByArray = (FindBy[]) findBys.value();
		By[] byArray = new By[findByArray.length];
		for (int i = 0; i < findByArray.length; i++) {
			byArray[i] = buildByFromFindBy(findByArray[i]);
		}

		return (By) ((org.openqa.selenium.By)new ByChained(byArray));
	}

	protected By buildBysFromFindByOneOf(FindAll findBys) {
		assertValidFindAll(findBys);

		FindBy[] findByArray = (FindBy[]) findBys.value();
		By[] byArray = new By[findByArray.length];
		for (int i = 0; i < findByArray.length; i++) {
			byArray[i] = buildByFromFindBy(findByArray[i]);
		}

		return (By) ((org.openqa.selenium.By) new ByAll(byArray));
	}

	protected By buildByFromFindBy(FindBy findBy) {
		assertValidFindBy(findBy);

		By ans = buildByFromShortFindBy(findBy);
		if (ans == null) {
			ans = buildByFromLongFindBy(findBy);
		}

		return ans;
	}

  protected By buildByFromLongFindBy(FindBy findBy) {
    How how = findBy.how();
    String using = findBy.using();

    switch (how) {
      case CLASS_NAME:
        return (By) By.className(using);

      case CSS:
        return (By) By.cssSelector(using);

      case ID:
        return (By) By.id(using);

      case ID_OR_NAME:    	   
        return (By) ((org.openqa.selenium.By)new ByIdOrName(using));

      case LINK_TEXT:
        return (By) By.linkText(using);

      case NAME:
        return (By) By.name(using);

      case PARTIAL_LINK_TEXT:
        return (By) By.partialLinkText(using);

      case TAG_NAME:
        return (By) By.tagName(using);

      case XPATH:
        return (By) By.xpath(using);

      default:
        // Note that this shouldn't happen (eg, the above matches all
        // possible values for the How enum)
        throw new IllegalArgumentException("Cannot determine how to locate element " + field);
    }
  }

	protected By buildByFromShortFindBy(FindBy findBy) {
		if (!"".equals(findBy.className()))
			return (By) By.className(findBy.className());

		if (!"".equals(findBy.css()))
			return (By) By.cssSelector(findBy.css());

		if (!"".equals(findBy.id()))
			return (By) By.id(findBy.id());

		if (!"".equals(findBy.linkText()))
			return (By) By.linkText(findBy.linkText());

		if (!"".equals(findBy.name()))
			return (By) By.name(findBy.name());

		if (!"".equals(findBy.partialLinkText()))
			return (By) By.partialLinkText(findBy.partialLinkText());

		if (!"".equals(findBy.tagName()))
			return (By) By.tagName(findBy.tagName());

		if (!"".equals(findBy.xpath()))
			return (By) By.xpath(findBy.xpath());		

		// Fall through
		return null;
	}

	private void assertValidAnnotations() {
		FindBys findBys = field.getAnnotation(FindBys.class);
		FindAll findAll = field.getAnnotation(FindAll.class);
		FindBy findBy = field.getAnnotation(FindBy.class);
		if (findBys != null && findBy != null) {
			throw new IllegalArgumentException(
					"If you use a '@FindBys' annotation, "
							+ "you must not also use a '@FindBy' annotation");
		}
		if (findAll != null && findBy != null) {
			throw new IllegalArgumentException(
					"If you use a '@FindAll' annotation, "
							+ "you must not also use a '@FindBy' annotation");
		}
		if (findAll != null && findBys != null) {
			throw new IllegalArgumentException(
					"If you use a '@FindAll' annotation, "
							+ "you must not also use a '@FindBys' annotation");
		}
	}

	private void assertValidFindBys(FindBys findBys) {
		for (FindBy findBy : findBys.value()) {
			assertValidFindBy(findBy);
		}
	}

	private void assertValidFindAll(FindAll findBys) {
		for (FindBy findBy : findBys.value()) {
			assertValidFindBy(findBy);
		}
	}

	private void assertValidFindBy(FindBy findBy) {
		if (findBy.how() != null) {
			if (findBy.using() == null) {
				throw new IllegalArgumentException(
						"If you set the 'how' property, you must also set 'using'");
			}
		}

		Set<String> finders = new HashSet<String>();
		if (!"".equals(findBy.using()))
			finders.add("how: " + findBy.using());
		if (!"".equals(findBy.className()))
			finders.add("class name:" + findBy.className());
		if (!"".equals(findBy.css()))
			finders.add("css:" + findBy.css());
		if (!"".equals(findBy.id()))
			finders.add("id: " + findBy.id());
		if (!"".equals(findBy.linkText()))
			finders.add("link text: " + findBy.linkText());
		if (!"".equals(findBy.name()))
			finders.add("name: " + findBy.name());
		if (!"".equals(findBy.partialLinkText()))
			finders.add("partial link text: " + findBy.partialLinkText());
		if (!"".equals(findBy.tagName()))
			finders.add("tag name: " + findBy.tagName());
		if (!"".equals(findBy.xpath()))
			finders.add("xpath: " + findBy.xpath());		

		if (finders.size() > 1) {
			throw new IllegalArgumentException(
					String.format(
							"You must specify at most one location strategy. Number found: %d (%s)",
							finders.size(), finders.toString()));
		}
	}
}

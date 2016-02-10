package com.automation.framework.web;


/**
 * Provides methods to interact with a web page. All HTML element (ButtonElement, LinkElement, TextFieldElement, etc.)
 * extends from this class.
 */
public class PageElement {

	public static enum LocatorType {
		ID,
		NAME,
		CLASS_NAME,
		LINK_TEXT,
		PARTIAL_LINK_TEXT,
		CSS_SELECTOR,
		TAG_NAME,
		XPATH,
	};

	private String name = null;
	private String value = null;
	LocatorType locatorType=null;
	
	public PageElement(String name, String value,LocatorType type) {
		this.name = name;
		this.value = value;
		this.locatorType= type;	
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

}

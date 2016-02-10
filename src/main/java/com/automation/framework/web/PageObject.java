package com.automation.framework.web;

import com.automation.framework.core.SeleniumTestContext;

public class PageObject {

	public WebCommand command(){
		return SeleniumTestContext.get().getWebCommand();
	}
}

package com.automation.framework.core;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;

public class TestSetup {
	
	@BeforeScenario
	public void setUp(){
		SeleniumTestContext.get().getWebdriver().manage().window().maximize();
	}
	
	@AfterScenario
	public void cleanUp(){
		SeleniumTestContext.get().getWebdriver().quit();
		cleanSession();
	}

	public void cleanSession(){
		SeleniumTestContext.thread.remove();
	}

}

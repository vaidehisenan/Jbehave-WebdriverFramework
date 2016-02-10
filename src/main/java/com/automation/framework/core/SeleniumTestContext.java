package com.automation.framework.core;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.automation.framework.web.WebCommand;


public class SeleniumTestContext {

	public static ThreadLocal<SeleniumTestContext> thread = new ThreadLocal<SeleniumTestContext>() {
		protected  SeleniumTestContext initialValue() {
			return new SeleniumTestContext();
		}
	};

	WebCommand webCommand;
	WebDriver driver;
	public SeleniumTestContext() {
		try {
			DesiredCapabilities desiredCapabilities=DesiredCapabilities.chrome();
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
			webCommand=new WebCommand(driver);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public static SeleniumTestContext get(){
		return thread.get();
	}

	public WebCommand getWebCommand(){
		return webCommand;
	}
	
	public WebDriver getWebdriver(){
		return driver;
	}
}

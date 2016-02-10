package com.automation.framework.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebCommand {

	private WebDriver driver;

	public WebCommand(WebDriver driver) {
		this.driver=driver;
	}
	
	public WebElement getWebElement(PageElement pageElement){
		WebElement webElement=null;
		
		switch (pageElement.locatorType) {
		case XPATH:
			webElement=driver.findElement(By.xpath(pageElement.getValue()));
			break;
		case ID:
			webElement=driver.findElement(By.id(pageElement.getValue()));
			break;
			
		default:
			break;
		}
		return webElement;
	}
	
	public void click(PageElement pageElement){
		getWebElement(pageElement).click();
	}
	
	public void type(PageElement pageElement,String value){
		getWebElement(pageElement).sendKeys(value);
	}
	
	public void goTo(String url){
		driver.get(url);
	}
	
	public void maximizeWindow(){
		driver.manage().window().maximize();
	}
}

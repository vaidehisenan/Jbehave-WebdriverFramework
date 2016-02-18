package com.automation.framework.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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
		case NAME:
			webElement=driver.findElement(By.name(pageElement.getValue()));
			break;
		case CLASS_NAME:
			webElement=driver.findElement(By.className(pageElement.getValue()));
			break;
		case LINK_TEXT:
			webElement=driver.findElement(By.linkText(pageElement.getValue()));
			break;
		case PARTIAL_LINK_TEXT:
			webElement=driver.findElement(By.partialLinkText(pageElement.getValue()));
			break;
		case CSS_SELECTOR:
			webElement=driver.findElement(By.cssSelector(pageElement.getValue()));
			break;
		case TAG_NAME:
			webElement=driver.findElement(By.tagName(pageElement.getValue()));
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

	public void selectUsingIndex(PageElement pageElement, int index) {
		WebElement webElement=getWebElement(pageElement);
		Select select = new Select(webElement);
		select.selectByIndex(index);
	}

	public void switchToFrame(PageElement pageElement) {
		WebElement webElement=getWebElement(pageElement);
		driver.switchTo().frame(webElement);
	}

}

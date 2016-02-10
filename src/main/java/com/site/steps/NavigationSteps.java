package com.site.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.site.pages.Page;

public class NavigationSteps extends Page {
	
	
	@Given("User lands on home page")
	public void gotoHomePage(){
		homePage().goToHomePage();
	}

	@When("User register")
	public void registerParent(){
		System.out.println("User register");

	}

	@Then(value="verify user landing page")
	public void verifyLandingPage(){
		System.out.println("verify user landing page");

	}
}

package com.site.pages;

public class HomePage extends Page {

	public HomePage(){
		super();
		// TODO Auto-generated constructor stub
	}

	public HomePage goToHomePage(){
		command().goTo("http://jbehave.org/");
		return this;
	}
}

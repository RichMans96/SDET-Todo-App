package com.qa.Todo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateAList {
	
	@FindBy(xpath = "/html/body/nav/div/div/ul/li[1]/a")
	private WebElement home;
	
	@FindBy(id = "listname")
	WebElement listName;


	public void createUser(String name) {
		listName.sendKeys(name);
		listName.submit();
	}
	
	public void home() {
		home.click();
	}
}

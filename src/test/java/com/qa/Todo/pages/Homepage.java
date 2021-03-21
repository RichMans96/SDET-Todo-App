package com.qa.Todo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage {
	
	public static final String URL = "src/main/frontend/html/homepage.html";

	@FindBy(xpath = "/html/body/nav/div/div/ul/li[2]/a")
	private WebElement createAList;

	@FindBy(xpath = "/html/body/nav/div/div/ul/li[3]/a")
	private WebElement createATodo;
	
	@FindBy(xpath = "/html/body/nav/div/div/ul/li[1]/a")
	private WebElement home;

	public void createTodoLink() {
		createATodo.click();
	}

	public void createListLink() {
		createAList.click();
	}
	
	public void home() {
		home.click();
	}
}

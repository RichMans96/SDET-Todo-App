package com.qa.Todo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateATodo {
	@FindBy(id = "todoData")
	WebElement todoData;
	
	@FindBy(id = "todoStatus")
	WebElement todoStatus;
	
	@FindBy(id = "listId")
	WebElement listId;


	public void createUser(String data, String status, String id) {
		todoData.sendKeys(data);
		todoStatus.sendKeys(status);
		listId.sendKeys(id);
		listId.submit();
	}
}

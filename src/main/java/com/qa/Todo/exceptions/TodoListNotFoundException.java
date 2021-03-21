package com.qa.Todo.exceptions;

import javax.persistence.EntityNotFoundException;

public class TodoListNotFoundException extends EntityNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7809527442032873439L;

	public TodoListNotFoundException() {
		super();
	}

	public TodoListNotFoundException(String message) {
		super(message);
	}

	
}

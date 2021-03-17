package com.qa.Todo.exceptions;

import javax.persistence.EntityNotFoundException;

public class TodoListNotFoundException extends EntityNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7809527442032873439L;

	public TodoListNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TodoListNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}

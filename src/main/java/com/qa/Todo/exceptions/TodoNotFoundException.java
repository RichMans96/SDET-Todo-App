package com.qa.Todo.exceptions;

import javax.persistence.EntityNotFoundException;

public class TodoNotFoundException extends EntityNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8801155064349632992L;

	public TodoNotFoundException() {
		super();
	}

	public TodoNotFoundException(String message) {
		super(message);
	}

}

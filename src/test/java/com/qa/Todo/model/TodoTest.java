package com.qa.Todo.model;

import org.junit.jupiter.api.Test;

import com.qa.Todo.data.model.Todo;
import com.qa.Todo.data.model.TodoList;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TodoTest {
	
	TodoList todoList = new TodoList(1, "Shopping");

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Todo.class).withPrefabValues(Todo.class, new Todo(1, "Buy milk", true, todoList), new Todo(2, "Buy beans", false, todoList)).verify();
	}
}

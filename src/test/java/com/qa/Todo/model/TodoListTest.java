package com.qa.Todo.model;


import org.junit.jupiter.api.Test;


import com.qa.Todo.data.model.TodoList;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TodoListTest {
	
	
	@Test
	public void testEquals() {
		
		EqualsVerifier.simple().forClass(TodoList.class).withPrefabValues(TodoList.class, new TodoList(1, "Shopping"), new TodoList(2, "Morning")).verify();
	}
}

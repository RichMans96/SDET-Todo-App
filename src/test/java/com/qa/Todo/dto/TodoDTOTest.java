package com.qa.Todo.dto;

import org.junit.jupiter.api.Test;

import com.qa.Todo.data.model.TodoList;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TodoDTOTest {

	TodoList todoList = new TodoList(1, "Shopping");

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(TodoDTO.class).withPrefabValues(TodoDTO.class, new TodoDTO(1, "Buy milk", true), new TodoDTO(2, "Buy beans", false)).verify();
	}
}

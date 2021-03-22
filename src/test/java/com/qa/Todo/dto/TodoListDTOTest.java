package com.qa.Todo.dto;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TodoListDTOTest {

	private TodoDTO todoDTO;

	private List<TodoDTO> todoDTOs;

	@Test
	public void testEquals() {
		todoDTO = new TodoDTO(1, "Brush teeth", true);

		todoDTOs = new ArrayList<TodoDTO>();

		todoDTOs.add(todoDTO);

		EqualsVerifier.simple().forClass(TodoListDTO.class).withPrefabValues(TodoListDTO.class,
				new TodoListDTO(1, "Shopping", todoDTOs), new TodoListDTO(2, "Morning", todoDTOs)).verify();
	}
}

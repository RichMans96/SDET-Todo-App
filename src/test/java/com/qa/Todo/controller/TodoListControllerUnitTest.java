package com.qa.Todo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.Todo.data.model.TodoList;
import com.qa.Todo.dto.TodoDTO;
import com.qa.Todo.dto.TodoListDTO;
import com.qa.Todo.service.TodoListService;

@SpringBootTest
public class TodoListControllerUnitTest {

	@Autowired
	private TodoListController todoListController;

	@MockBean
	private TodoListService todoListService;

	private List<TodoList> todoLists;
	private List<TodoListDTO> todoListDTOs;
	private List<TodoDTO> todos;

	private TodoDTO todo;
	private TodoList todoList;
	private TodoListDTO todoListDTO;

	@BeforeEach
	public void init() {
		todo = new TodoDTO(1, "brush teeth", false);
		todoList = new TodoList(1, "Morning");
		todoListDTO = new TodoListDTO(1, "Morning", todos);

		todos = new ArrayList<TodoDTO>();
		todoLists = new ArrayList<TodoList>();
		todoListDTOs = new ArrayList<TodoListDTO>();

		todos.add(todo);
		todoLists.add(todoList);
		todoListDTOs.add(todoListDTO);
	}

	@Test
	public void getAllTodoLists() {
		when(todoListService.readAllTodoLists()).thenReturn(todoListDTOs);

		ResponseEntity<List<TodoListDTO>> response = new ResponseEntity<List<TodoListDTO>>(todoListDTOs, HttpStatus.OK);

		assertThat(response).isEqualTo(todoListController.getAllTodoLists());

		verify(todoListService, times(1)).readAllTodoLists();
	}

	@Test
	public void createTodoListTest() {
		when(todoListService.createTodoList(Mockito.any(TodoList.class))).thenReturn(todoListDTO);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(todoListDTO.getListId()));

		ResponseEntity<TodoListDTO> response = new ResponseEntity<TodoListDTO>(todoListDTO, headers,
				HttpStatus.CREATED);

		assertThat(response).isEqualTo(todoListController.createTodoList(todoList));

		verify(todoListService, times(1)).createTodoList(Mockito.any(TodoList.class));
	}

	@Test
	public void updateTodoListTest() {
		when(todoListService.updateTodoList(Mockito.any(Integer.class), Mockito.any(TodoList.class)))
				.thenReturn(todoListDTO);

		ResponseEntity<TodoListDTO> response = new ResponseEntity<TodoListDTO>(todoListDTO, HttpStatus.OK);

		assertThat(response).isEqualTo(todoListController.updateTodoList(todoList.getListId(), todoList));

		verify(todoListService, times(1)).updateTodoList(Mockito.any(Integer.class), Mockito.any(TodoList.class));
	}

	@Test
	public void deleteTodoListTest() {
		when(todoListService.deleteList(Mockito.any(Integer.class))).thenReturn(true);

		ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(true, HttpStatus.OK);

		assertThat(response).isEqualTo(todoListController.deleteTodoList(todoList.getListId()));

		verify(todoListService, times(1)).deleteList(Mockito.any(Integer.class));
	}
}

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

import com.qa.Todo.data.model.Todo;
import com.qa.Todo.data.model.TodoList;
import com.qa.Todo.dto.TodoDTO;
import com.qa.Todo.service.TodoService;

@SpringBootTest
public class TodoControllerUnitTest {
	
	@Autowired
	private TodoController todoController;
	
	@MockBean
	private TodoService todoService;
	
	private List<Todo> todos;
	private List<TodoDTO> todoDTOs;
	
	private Todo todo;
	private TodoDTO todoDTO;
	private TodoList todoList;

	@BeforeEach
	public void init() {
		todoList = new TodoList(1, "Morning");
		todo = new Todo(1, "Brush teeth", true, todoList);
		todoDTO = new TodoDTO(1, "Brush teeth", true);
		
		todos = new ArrayList<Todo>();
		todoDTOs = new ArrayList<TodoDTO>();
	}
	
	@Test
	public void getAllTodosTest() {
		when(todoService.readAllTodos()).thenReturn(todoDTOs);
		
		ResponseEntity<List<TodoDTO>> response = new ResponseEntity<List<TodoDTO>>(todoDTOs, HttpStatus.OK);
		
		assertThat(response).isEqualTo(todoController.getAllTodos());
		
		verify(todoService, times(1)).readAllTodos();
	}
	
	@Test
	public void getTodoByIdTest() {
		when(todoService.todoById(Mockito.any(Integer.class))).thenReturn(todoDTO);
		
		ResponseEntity<TodoDTO> response = new ResponseEntity<TodoDTO>(todoDTO, HttpStatus.OK);
	
		assertThat(response).isEqualTo(todoController.todoById(todo.getId()));
		
		verify(todoService, times(1)).todoById(Mockito.any(Integer.class));
	}
	
	@Test
	public void createTodoTest() {
		when(todoService.createTodo(Mockito.any(Todo.class))).thenReturn(todoDTO);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(todoDTO.getId()));
		
		ResponseEntity<TodoDTO> response = new ResponseEntity<TodoDTO>(todoDTO, headers, HttpStatus.CREATED);
		
		assertThat(response).isEqualTo(todoController.createTodo(todo));
		
		verify(todoService, times(1)).createTodo(Mockito.any(Todo.class));
	}
}

package com.qa.Todo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.Todo.data.model.Todo;
import com.qa.Todo.data.model.TodoList;
import com.qa.Todo.data.repository.TodoRepository;
import com.qa.Todo.dto.TodoDTO;
import com.qa.Todo.mappers.TodoMapper;

@SpringBootTest
public class TodoServiceUnitTest {

	@Autowired
	private TodoService todoService;
	
	@MockBean
	private TodoRepository todoRepository;
	
	@MockBean
	private TodoMapper todoMapper;
	
	private List<Todo> todos;
	private List<TodoDTO> todoDTOs;
	
	private Todo todo;
	private Optional<Todo> optTodo;
	private TodoDTO todoDTO;
	private TodoList todoList;
	
	@BeforeEach
	public void init() {
		todoList = new TodoList(1, "Morning");
		todo = new Todo(1, "Brush teeth", true, todoList);
		todoDTO = new TodoDTO(1, "Brush teeth", true);
		optTodo = Optional.of(new Todo(1, "Brush teeth", true, todoList));
		
		
		todos = new ArrayList<Todo>();
		todoDTOs = new ArrayList<TodoDTO>();
		
		
		todos.add(todo);
		todoDTOs.add(todoDTO);

	}
	
	@Test
	public void readAllTodosTest() {
		when(todoRepository.findAll()).thenReturn(todos);
		when(todoMapper.mapToDTO(todo)).thenReturn(todoDTO);
		
		assertThat(todoDTOs).isEqualTo(todoService.readAllTodos());
		
		verify(todoRepository, times(1)).findAll();
		verify(todoMapper, times(1)).mapToDTO(todo);
	}
	
	@Test
	public void todoByIdExceptionTest() {
		when(todoRepository.findById(Mockito.any(Integer.class))).thenReturn(optTodo);
		when(todoMapper.mapToDTO(todo)).thenReturn(todoDTO);
		
		assertEquals(todoDTO, todoService.todoById(todo.getId()));
		
		verify(todoRepository, times(1)).findById(Mockito.any(Integer.class));
		verify(todoMapper, times(1)).mapToDTO(todo);
	}
	
	@Test
	public void updateTodoTest() {
		Todo updatedTodo = new Todo(1, "Get Dressed", true, todoList);
		TodoDTO updatedTodoDTO = new TodoDTO(1, "Get Dressed", true);
	
		when(todoRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(todo));
		when(todoRepository.save(Mockito.any(Todo.class))).thenReturn(updatedTodo);
		when(todoMapper.mapToDTO(Mockito.any(Todo.class))).thenReturn(updatedTodoDTO);
		
		TodoDTO testDTO = todoService.updateTodo(todo.getId(), updatedTodo);
		
		assertThat(updatedTodoDTO).isEqualTo(testDTO);
		
	}
}

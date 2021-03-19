package com.qa.Todo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.Todo.data.model.TodoList;
import com.qa.Todo.data.repository.TodoListRepository;
import com.qa.Todo.dto.TodoDTO;
import com.qa.Todo.dto.TodoListDTO;
import com.qa.Todo.mappers.TodoListMapper;

@SpringBootTest
public class TodoListServiceUnitTest {

	@Autowired
	private TodoListService todoListService;
	
	@MockBean
	private TodoListRepository todoListRepository;
	
	@MockBean
	private TodoListMapper todoListMapper;
	
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
	public void readAllTodoListsTest() {
		when(todoListRepository.findAll()).thenReturn(todoLists);
		when(todoListMapper.mapToDTO(todoList)).thenReturn(todoListDTO);
		
		assertThat(todoListDTOs).isEqualTo(todoListService.readAllTodoLists());
		
		verify(todoListRepository, times(1)).findAll();
		verify(todoListMapper, times(1)).mapToDTO(todoList);
	}
}

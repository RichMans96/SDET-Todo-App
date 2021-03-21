package com.qa.Todo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.Todo.data.model.TodoList;
import com.qa.Todo.data.repository.TodoListRepository;
import com.qa.Todo.data.repository.TodoRepository;
import com.qa.Todo.dto.TodoDTO;
import com.qa.Todo.dto.TodoListDTO;
import com.qa.Todo.mappers.TodoListMapper;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class TodoListIntegrationTest {

	@Autowired
	private TodoListService todoListService;
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private TodoListRepository todoListRepository;
	
	@Autowired
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
		
		todoListRepository.deleteAll();
		todoList = todoListRepository.save(todoList);
		
		todoRepository.deleteAll();
		
		todos.add(todo);
		todoLists.add(todoList);
		todoListDTOs.add(todoListDTO);
	}
	
	@Test
	public void createTodoListTest() {
		TodoList newTodoList = new TodoList("Shopping");
		TodoListDTO newTodoListDTO = todoListMapper.mapToDTO(newTodoList);
		
		TodoListDTO createdTodo = todoListService.createTodoList(newTodoList);
		newTodoListDTO.setListId(createdTodo.getListId());
		
		assertThat(newTodoListDTO).isEqualTo(createdTodo);
	}
	
	@Test
	public void deleteTodoListTest() {
		boolean deletedTodoList = todoListService.deleteList(todoList.getListId());
		
		assertThat(deletedTodoList).isEqualTo(true);
	}
	
	@Test 
	void updateTodoListTest() {
		TodoList updatedTodoList = new TodoList(1, "Nighttime");
		TodoListDTO updatedTodoListDTO = new TodoListDTO(1, "Nighttime", todos);
		
		TodoListDTO testListDTO = todoListService.updateTodoList(todoList.getListId(), updatedTodoList);
		testListDTO.setTodos(todos);
		testListDTO.setListId(1);
		assertThat(updatedTodoListDTO).isEqualTo(testListDTO);
	}
	
	@Test
	public void readAllTodoListTest() {
		List<TodoListDTO> listsInDb = todoListService.readAllTodoLists();
		
		assertThat(todoListDTOs).isEqualTo(listsInDb);
	}
	
	@Test
	public void getByIdTest() {
		TodoListDTO foundList = todoListService.getById(todoList.getListId());
		
		assertThat(todoListDTO).isEqualTo(foundList);
	}

}

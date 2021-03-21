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

import com.qa.Todo.data.model.Todo;
import com.qa.Todo.data.model.TodoList;
import com.qa.Todo.data.repository.TodoListRepository;
import com.qa.Todo.data.repository.TodoRepository;
import com.qa.Todo.dto.TodoDTO;
import com.qa.Todo.mappers.TodoMapper;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class TodoServiceIntegrationTest {
	@Autowired
	private TodoService todoService;
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private TodoListRepository todoListRepository;
	
	@Autowired
	private TodoMapper todoMapper;
	
	private List<Todo> todos;
	private List<TodoDTO> todoDTOs;
	
	private Todo todo;
	private TodoDTO todoDTO;
	private TodoList todoList;
	
	@BeforeEach
	public void init() {
		todoList = new TodoList("Morning");
		todo = new Todo("Brush Teeth", true, todoList);
		
		todos = new ArrayList<Todo>();
		todoDTOs = new ArrayList<TodoDTO>();
		
		todoListRepository.deleteAll();
		todoList = todoListRepository.save(todoList);
		
		todoRepository.deleteAll();
		
		todo = todoRepository.save(todo);
		todoDTO = todoMapper.mapToDTO(todo);
		
		todos.add(todo);
		todoDTOs.add(todoDTO);
	}
	
	@Test
	public void createTodoTest() {
		Todo newTodo = new Todo("Get dressed", true, todoList);
		TodoDTO newTodoDTO = todoMapper.mapToDTO(newTodo);
		
		TodoDTO createdTodo = todoService.createTodo(newTodo);
		newTodoDTO.setId(createdTodo.getId());
		
		assertThat(newTodoDTO).isEqualTo(createdTodo);
	}
	
	@Test
	public void readAllTodosTest() {
		List<TodoDTO> todosInDb = todoService.readAllTodos();
		
		assertThat(todoDTOs).isEqualTo(todosInDb);
	}
	
	@Test
	public void getByIdTest() {
		TodoDTO foundTodo = todoService.todoById(todo.getId());
		
		assertThat(todoDTO).isEqualTo(foundTodo);
	}
	
	@Test
	public void updateTodoTest() {
		Todo updatedTodo = new Todo(1, "Get dressed", false, todoList);
		TodoDTO updatedTodoDTO = new TodoDTO(3, "Get dressed", false);
		
		TodoDTO testDTO = todoService.updateTodo(todo.getId(), updatedTodo);
		
		assertThat(updatedTodoDTO).isEqualTo(testDTO);
	}

	@Test
	public void deleteTodoTest() {
		boolean deleteTodo = todoService.deleteTodo(todo.getId());
		assertThat(deleteTodo).isEqualTo(true);
	}
}

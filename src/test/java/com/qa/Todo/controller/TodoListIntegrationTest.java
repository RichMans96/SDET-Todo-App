package com.qa.Todo.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.Todo.data.model.TodoList;
import com.qa.Todo.dto.TodoDTO;
import com.qa.Todo.dto.TodoListDTO;
import com.qa.Todo.mappers.TodoListMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema.sql",
"classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class TodoListIntegrationTest {
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private TodoListMapper todoListMapper;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	private TodoList todoList = new TodoList(1, "Shopping");
	private List<TodoList> todoLists = List.of(todoList);
	
	private TodoDTO todoDTO = new TodoDTO(1, "Get Milk", true);
	private List<TodoDTO> todoDTOs = List.of(todoDTO);
	
	private TodoListDTO todoListDTO = new TodoListDTO(1, "Shopping", todoDTOs);
	private List<TodoListDTO> todoListDTOs = List.of(todoListDTO);
	

	@Test
	public void createTodoListTest() throws Exception {
		TodoList testTodoList = new TodoList(2, "Morning");
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/todolist");
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(testTodoList));
		
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(testTodoList));
		ResultMatcher headerMatcher = MockMvcResultMatchers.header().string("Location", "2");
	
		mvc.perform(mockRequest).andExpect(headerMatcher).andExpect(contentMatcher).andExpect(statusMatcher);
	}
	
	
	@Test
	public void readAllTodosTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/todolist");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(todoListDTOs));
	
		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	@Test
	public void deleteTodoListTest() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/todolist/1");
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("true");
	
		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	@Test
	public void updateTodoListTest() throws Exception {
		TodoList updatedTodoList = new TodoList(1, "Go to Tesco");
		TodoListDTO exceptedTodoList = new TodoListDTO(1, "Go to Tesco", todoDTOs);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT, "/todolist/1");
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(updatedTodoList));
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(exceptedTodoList));
	
		mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	
}

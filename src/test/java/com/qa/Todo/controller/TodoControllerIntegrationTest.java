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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.Todo.data.model.Todo;
import com.qa.Todo.data.model.TodoList;
import com.qa.Todo.dto.TodoDTO;
import com.qa.Todo.mappers.TodoMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema.sql",
"classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class TodoControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private TodoMapper todoMapper;

	@Autowired
	private ObjectMapper objectMapper;

	private TodoList todoList = new TodoList(1, "Morning");
	private Todo todo = new Todo(1, "Brush teeth", true, todoList);
	private TodoDTO todoDTO = new TodoDTO(1, "Brush teeth", true);

	private List<Todo> todos = List.of(todo);
	private List<TodoDTO> todoDTOs = List.of(todoDTO);

	@Test
	public void createTodoTest() throws Exception {
		Todo testTodo = new Todo("Get dressed", true, todoList);
		TodoDTO testTodoDTO = new TodoDTO(2, "Get dressed", true);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/todo");
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(testTodo));

		mockRequest.accept(MediaType.APPLICATION_JSON);

		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(testTodoDTO));
		ResultMatcher headerMatcher = MockMvcResultMatchers.header().string("Location", "2");

		mvc.perform(mockRequest).andExpect(headerMatcher).andExpect(contentMatcher).andExpect(statusMatcher);
	}
}

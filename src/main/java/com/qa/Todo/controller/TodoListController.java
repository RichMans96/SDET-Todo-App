package com.qa.Todo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.Todo.data.model.TodoList;
import com.qa.Todo.dto.TodoListDTO;
import com.qa.Todo.service.TodoListService;

@RestController
@RequestMapping("/todolist")
public class TodoListController {
	private TodoListService todoListService;
	
	@Autowired
	public TodoListController(TodoListService todoListService) {
		this.todoListService = todoListService;
	}
	
	@PostMapping
	public ResponseEntity<TodoListDTO> createTodoList(@Valid @RequestBody TodoList todoList) {
		TodoListDTO newTodoList = todoListService.createTodoList(todoList);
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Location", String.valueOf(newTodoList.getListId()));
		
		return new ResponseEntity<TodoListDTO>(newTodoList, headers, HttpStatus.CREATED);
	}
}

package com.qa.Todo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.Todo.data.model.Todo;
import com.qa.Todo.dto.TodoDTO;
import com.qa.Todo.service.TodoService;

@RestController
@RequestMapping(path = "/todo")
@CrossOrigin
public class TodoController {
	
	private TodoService todoService;
	
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@PostMapping
	public ResponseEntity<TodoDTO> createTodo(@Valid @RequestBody Todo todo) {
		TodoDTO newTodo = todoService.createTodo(todo);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(newTodo.getId()));
		
		return new ResponseEntity<TodoDTO>(newTodo, headers, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity <List<TodoDTO>> getAllTodos() {
		List<TodoDTO> todos = todoService.readAllTodos();
		
		return new ResponseEntity<List<TodoDTO>>(todos, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TodoDTO> todoById(@PathVariable("id") Integer id) {
		TodoDTO todo = todoService.todoById(id);
		
		return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TodoDTO> updateTodo(@PathVariable("id") Integer id, @RequestBody Todo todo) {
		TodoDTO updatedTodo = todoService.updateTodo(id, todo);
		
		return new ResponseEntity<TodoDTO>(updatedTodo, HttpStatus.OK);
	}

}

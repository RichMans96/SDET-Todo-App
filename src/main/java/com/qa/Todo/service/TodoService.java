package com.qa.Todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.Todo.data.model.Todo;
import com.qa.Todo.data.repository.TodoRepository;
import com.qa.Todo.dto.TodoDTO;
import com.qa.Todo.mappers.TodoMapper;

@Service
public class TodoService {
	
	private TodoRepository todoRepository;
	
	private TodoMapper todoMapper;
	
	@Autowired
	public TodoService(TodoRepository todoRepository, TodoMapper todoMapper) {
		this.todoRepository = todoRepository;
		this.todoMapper = todoMapper;
	}
	
	public TodoDTO createTodo(Todo todo) {
		Todo newTodo = todoRepository.save(todo);
		
		return todoMapper.mapToDTO(newTodo);
	}
}

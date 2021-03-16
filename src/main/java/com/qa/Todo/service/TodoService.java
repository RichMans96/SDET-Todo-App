package com.qa.Todo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.Todo.data.model.Todo;
import com.qa.Todo.data.repository.TodoRepository;
import com.qa.Todo.dto.TodoDTO;
import com.qa.Todo.exceptions.TodoNotFoundException;
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
	
	public List<TodoDTO> readAllTodos() {
		List<Todo> todos = todoRepository.findAll();
		List<TodoDTO> todoDTOs = new ArrayList<TodoDTO>();
		
		todos.forEach(todo -> todoDTOs.add(todoMapper.mapToDTO(todo)));
		
		return todoDTOs;
	}
	
	public TodoDTO todoById(Integer id) {
		Optional<Todo> todo = todoRepository.findById(id);
		
		if(todo.isPresent()) {
			return todoMapper.mapToDTO(todo.get());
		} else {
			throw new TodoNotFoundException("Todo doesn't exist!");
		}
	}
}

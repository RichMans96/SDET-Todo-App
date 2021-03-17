package com.qa.Todo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.Todo.data.model.TodoList;
import com.qa.Todo.data.repository.TodoListRepository;
import com.qa.Todo.dto.TodoListDTO;
import com.qa.Todo.mappers.TodoListMapper;

@Service
public class TodoListService {

	private TodoListRepository todoListRepository;
	
	private TodoListMapper todoListMapper;
	
	@Autowired
	public TodoListService(TodoListRepository todoListRepository, TodoListMapper todoListMapper) {
		this.todoListMapper = todoListMapper;
		this.todoListRepository = todoListRepository;
	}
	
	public TodoListDTO createTodoList(TodoList todoList) {
		TodoList createdTodoList = todoListRepository.save(todoList);
		
		return todoListMapper.mapToDTO(createdTodoList);
	}
	
	@Transactional
	public List<TodoListDTO> readAllTodoLists() {
		List<TodoList> listsInDb = todoListRepository.findAll();
		List<TodoListDTO> returnedLists = new ArrayList<TodoListDTO>();
		
		listsInDb.forEach(list -> {
			returnedLists.add(todoListMapper.mapToDTO(list));
		});
		System.out.println(listsInDb);
		System.out.println(returnedLists);
		return returnedLists;
	}
	
}

package com.qa.Todo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.Todo.data.model.TodoList;
import com.qa.Todo.data.repository.TodoListRepository;
import com.qa.Todo.dto.TodoListDTO;
import com.qa.Todo.exceptions.TodoListNotFoundException;
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
	
	public TodoListDTO getById(Integer id) {
		Optional<TodoList> todoList = todoListRepository.findById(id);
		
		if(todoList.isPresent()) {
			return todoListMapper.mapToDTO(todoList.get());
		} else {
			throw new TodoListNotFoundException("This list doesn't exist!");
		}
	}
	
	public boolean deleteList(Integer id) {
		if(!todoListRepository.existsById(id)) {
			throw new TodoListNotFoundException("Doesn't exist");
		}
		todoListRepository.deleteById(id);
		boolean exists = todoListRepository.existsById(id);
		
		return !exists;
	}
	
	public TodoListDTO updateTodoList(Integer id, TodoList todoList) throws TodoListNotFoundException {
		Optional<TodoList> todoListToUpdate = todoListRepository.findById(id);
		TodoList dbTodoList;
		
		if(todoListToUpdate.isPresent()) {
			dbTodoList = todoListToUpdate.get();
		} else {
			throw new TodoListNotFoundException("Doesn't exist");
		}
		
		dbTodoList.setListName(todoList.getListName());
		
		TodoList updatedTodoList = todoListRepository.save(dbTodoList);
		return todoListMapper.mapToDTO(updatedTodoList);
	}
}

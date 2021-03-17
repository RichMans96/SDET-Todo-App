package com.qa.Todo.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qa.Todo.data.model.TodoList;
import com.qa.Todo.dto.TodoListDTO;



@Component
public class TodoListMapper {

	private ModelMapper modelMapper;
	
	@Autowired
	public TodoListMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public TodoListDTO mapToDTO(TodoList todo) {
		return this.modelMapper.map(todo, TodoListDTO.class);
	}
	
	public TodoList mapToTodo(TodoListDTO todoDTO) {
		return this.modelMapper.map(todoDTO, TodoList.class);
	}
}

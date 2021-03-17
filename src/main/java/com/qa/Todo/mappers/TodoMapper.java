package com.qa.Todo.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qa.Todo.data.model.Todo;
import com.qa.Todo.dto.TodoDTO;

@Component
public class TodoMapper {

	private ModelMapper modelMapper;
	
	@Autowired
	public TodoMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public TodoDTO mapToDTO(Todo todo) {
		return this.modelMapper.map(todo, TodoDTO.class);
	}
	
	public Todo mapToTodo(TodoDTO todoDTO) {
		return this.modelMapper.map(todoDTO, Todo.class);
	}
	
}
package com.qa.Todo.dto;

import java.util.List;

public class TodoListDTO {
	
	private int listId;
	
	private String listName;
	
	private List<TodoDTO> todos;
	
	

	public TodoListDTO() {
		super();
	}



	public TodoListDTO(int listId, String listName, List<TodoDTO> todos) {
		super();
		this.listId = listId;
		this.listName = listName;
		this.todos = todos;
	}



	public int getListId() {
		return listId;
	}



	public void setListId(int listId) {
		this.listId = listId;
	}



	public String getListName() {
		return listName;
	}



	public void setListName(String listName) {
		this.listName = listName;
	}



	public List<TodoDTO> getTodos() {
		return todos;
	}



	public void setTodos(List<TodoDTO> todos) {
		this.todos = todos;
	}

	@Override
	public String toString() {
		return "TodoListDTO [listId=" + listId + ", listName=" + listName + ", todos=" + todos + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + listId;
		result = prime * result + ((listName == null) ? 0 : listName.hashCode());
		result = prime * result + ((todos == null) ? 0 : todos.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TodoListDTO other = (TodoListDTO) obj;
		if (listId != other.listId)
			return false;
		if (listName == null) {
			if (other.listName != null)
				return false;
		} else if (!listName.equals(other.listName))
			return false;
		if (todos == null) {
			if (other.todos != null)
				return false;
		} else if (!todos.equals(other.todos))
			return false;
		return true;
	}
	
	

}

package com.qa.Todo.data.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "todo_list")
public class TodoList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "list_id")
	private int listId;
	
	@Column(name = "list_name")
	@NotNull
	private String listName;
	
	@OneToMany(mappedBy = "todoList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Todo> todos;
	
	public TodoList() {
		
	}
	
	public TodoList(String listName) {
		super();
		this.listName = listName;
	}

	public TodoList(int listId, String listName) {
		super();
		this.listId = listId;
		this.listName = listName;
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

	public List<Todo> getTodos() {
		return todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + listId;
		result = prime * result + ((listName == null) ? 0 : listName.hashCode());
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
		TodoList other = (TodoList) obj;
		if (listId != other.listId)
			return false;
		if (listName == null) {
			if (other.listName != null)
				return false;
		} else if (!listName.equals(other.listName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TodoList [listId=" + listId + ", listName=" + listName + "]";
	}
	
	
}

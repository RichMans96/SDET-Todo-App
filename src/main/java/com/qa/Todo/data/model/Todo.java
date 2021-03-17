package com.qa.Todo.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Todo")
public class Todo {


		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "todo_id")
		private int id;
		
		@Column(name = "todo_data")
		@NotNull
		private String todoData;
		
		@Column(name = "todo_status")
		@NotNull
		private boolean todoStatus;
		
		@ManyToOne(targetEntity = TodoList.class, fetch = FetchType.EAGER)
		@JoinColumn(name = "fk_list_id")
		private TodoList todoList;
		
		public Todo() {}
		
		public Todo(String todoData,  boolean todoStatus, TodoList todoList) {
			super();
			this.todoData = todoData;
			this.todoStatus = todoStatus;
			this.todoList = todoList;
		}

		public Todo(int id,  String todoData,  boolean todoStatus, TodoList todoList) {
			super();
			this.id = id;
			this.todoData = todoData;
			this.todoStatus = todoStatus;
			this.todoList = todoList;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTodoData() {
			return todoData;
		}

		public void setTodoData(String todoData) {
			this.todoData = todoData;
		}

		public boolean getTodoStatus() {
			return todoStatus;
		}

		public void setTodoStatus(boolean todoStatus) {
			this.todoStatus = todoStatus;
		}

		public TodoList getTodoList() {
			return todoList;
		}
		
		public void setTodoList(TodoList todoList) {
			this.todoList = todoList;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			result = prime * result + ((todoData == null) ? 0 : todoData.hashCode());
			result = prime * result + ((todoList == null) ? 0 : todoList.hashCode());
			result = prime * result + (todoStatus ? 1231 : 1237);
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
			Todo other = (Todo) obj;
			if (id != other.id)
				return false;
			if (todoData == null) {
				if (other.todoData != null)
					return false;
			} else if (!todoData.equals(other.todoData))
				return false;
			if (todoList == null) {
				if (other.todoList != null)
					return false;
			} else if (!todoList.equals(other.todoList))
				return false;
			if (todoStatus != other.todoStatus)
				return false;
			return true;
		}
		
		
}

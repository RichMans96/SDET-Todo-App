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
		@JoinColumn
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

		public boolean isTodoStatus() {
			return todoStatus;
		}

		public void setTodoStatus(boolean todoStatus) {
			this.todoStatus = todoStatus;
		}

		public TodoList getList() {
			return todoList;
		}
		
		public void setList(TodoList todoList) {
			this.todoList = todoList;
		}
}

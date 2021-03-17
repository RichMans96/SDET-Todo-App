package com.qa.Todo.dto;

public class TodoDTO {


		private int id;

		private String todoData;

		private boolean todoStatus;


		public TodoDTO() {}


		public TodoDTO(int id,  String todoData,  boolean todoStatus) {
			super();
			this.id = id;
			this.todoData = todoData;
			this.todoStatus = todoStatus;
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


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			result = prime * result + ((todoData == null) ? 0 : todoData.hashCode());
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
			TodoDTO other = (TodoDTO) obj;
			if (id != other.id)
				return false;
			if (todoData == null) {
				if (other.todoData != null)
					return false;
			} else if (!todoData.equals(other.todoData))
				return false;
			if (todoStatus != other.todoStatus)
				return false;
			return true;
		}


		@Override
		public String toString() {
			return "TodoDTO [id=" + id + ", todoData=" + todoData + ", todoStatus=" + todoStatus + "]";
		}
		
		
}

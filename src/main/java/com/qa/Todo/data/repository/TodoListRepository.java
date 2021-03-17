package com.qa.Todo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.Todo.data.model.TodoList;

public interface TodoListRepository extends JpaRepository<TodoList, Integer> {

}

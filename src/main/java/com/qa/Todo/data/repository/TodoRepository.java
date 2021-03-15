package com.qa.Todo.data.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.Todo.data.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer>{
	
	@Query("SELECT t FROM Todo t")
	public List<Todo> getAllTodosJPQL();

}

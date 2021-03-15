package com.qa.Todo.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.Todo.data.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer>{


}

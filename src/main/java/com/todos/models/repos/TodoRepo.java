package com.todos.models.repos;

import org.springframework.data.repository.CrudRepository;

import com.todos.models.entities.Todo;

public interface TodoRepo extends CrudRepository<Todo, Long> {
    
}

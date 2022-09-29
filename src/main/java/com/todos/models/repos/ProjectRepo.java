package com.todos.models.repos;

import org.springframework.data.repository.CrudRepository;

import com.todos.models.entities.Project;

public interface ProjectRepo extends CrudRepository<Project, Long>  {
    
}

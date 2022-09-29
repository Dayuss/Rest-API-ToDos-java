package com.todos.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todos.models.entities.Project;
import com.todos.models.repos.ProjectRepo;

@Service
@Transactional
public class ProjectService {
    
    @Autowired
    private ProjectRepo projectRepo;

    public Project save(Project project){
        return projectRepo.save(project);
    }

    public Project getOne(Long id){
        return projectRepo.findById(id).get();
    }

    public Iterable<Project> getAll(){
        return projectRepo.findAll();
    }

    public void delete(Long id){
        projectRepo.deleteById(id);
    }


}

package com.todos.controllers;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todos.helpers.ResponseData;
import com.todos.models.entities.Project;
import com.todos.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseData<Iterable<Project>> getAll(){
        
        ResponseData<Iterable<Project>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(projectService.getAll());
        responseData.getMessage().add("Success get data.");
        return responseData;
    }

    @GetMapping("/:id")
    public ResponseData<Project> getOne(@PathParam("id") Long id){
        ResponseData<Project> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.getMessage().add("Success get data.");
        responseData.setPayload(projectService.getOne(id));
        return responseData;
    }

    @PostMapping
    public ResponseData<Project> create(@Valid @RequestBody Project project, Errors errors){
        ResponseData<Project> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
        }else{
            responseData.setStatus(true);
            responseData.getMessage().add("Success insert data.");
            project.setCreatedAt(new java.sql.Date(System.currentTimeMillis()));
            Project insert = projectService.save(project);
            responseData.setPayload(insert);
        }
        return responseData;
    }

    @PutMapping("/:id")
    public ResponseData<Project> update(@PathParam("id") Long id, @RequestBody Project project) {
        ResponseData<Project> responseData = new ResponseData<>();
        
        // response data
        responseData.setStatus(true);
        responseData.getMessage().add("Success update data.");

        // update to db
        project.setId(id);
        project.setUpdatedAt(new java.sql.Date(System.currentTimeMillis()));
        Project insert = projectService.save(project);


        responseData.setPayload(insert);
        return responseData;
    }

    @DeleteMapping("/:id")
    public ResponseData<Project> delete(@PathParam("id") Long id){

        projectService.delete(id);

        ResponseData<Project> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.getMessage().add("Delete success");
        responseData.setPayload(null);

        return responseData;

    }
    
}

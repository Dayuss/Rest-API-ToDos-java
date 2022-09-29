package com.todos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/welcome")
public class WlcomeController {

    @GetMapping
    public String welcome(){
        return "Hello World from spring boot!";
    }

}

package com.Valens.api1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/api")
    public String getHomePage(){
        return "Hello!! Welcome to our API";
    }
}

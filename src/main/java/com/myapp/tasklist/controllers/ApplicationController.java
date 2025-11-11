package com.myapp.tasklist.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {
    	
    @GetMapping("/")
	public String getWelcomeRoot() {
		return "Hello, welcome to root";
	}
    
}

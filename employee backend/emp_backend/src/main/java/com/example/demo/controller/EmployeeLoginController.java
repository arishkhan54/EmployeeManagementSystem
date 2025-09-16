package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeLoginController {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    // Employee Login endpoint
    @PostMapping("/employee/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Employee employee = employeeRepository.findByEmail(loginRequest.getEmail());
        
        if (employee != null && employee.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid login credentials");
        }
    }
}

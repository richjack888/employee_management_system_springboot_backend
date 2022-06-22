package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springbootbackend.repository.EmployeeRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {


    private EmployeeRepository employeeRepository;
    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // get all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}

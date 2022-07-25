package com.example.springbootbackend.controller;

import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://employee-manage-system-jack.herokuapp.com/")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    private EmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // read all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // creat employee
    @ResponseStatus( HttpStatus.CREATED )
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // read employee by id - Version 1
    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee ID not found:" + id));

    }

//    // read employee by id - version 2
//    @GetMapping("/employees/{id}")
//    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee ID not found:" + id));
//        return ResponseEntity.ok(employee);
//    }

    // update employee by id - version 1
    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee newEmployee) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee ID not found:" + id));

        employee.setFirstName(newEmployee.getFirstName());
        employee.setLastName(newEmployee.getLastName());
        employee.setemailId(newEmployee.getemailId());

        return employeeRepository.save(employee);
    }

////     update employee by id - version 2
//    @PutMapping("/employees/{id}")
//    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee newEmployee) {
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee ID not found:" + id));
//
//        employee.setFirstName(newEmployee.getFirstName());
//        employee.setLastName(newEmployee.getLastName());
//        employee.setemailId(newEmployee.getemailId());
//
//        Employee updateEmployee = employeeRepository.save(employee);
//
//        return ResponseEntity.ok(updateEmployee);
//    }

//    //     delete employees by id - version 1
//    @DeleteMapping("/employees/{id}")
//    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee ID not found:" + id));
//
//        employeeRepository.delete(employee);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("delete", Boolean.TRUE);
//        return ResponseEntity.ok(response);
//    }

        // delete employees by id - version 2
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Long> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee ID not found:" + id));

        employeeRepository.delete(employee);
        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }

}

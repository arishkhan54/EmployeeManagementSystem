package com.example.ems.service;

import com.example.ems.model.Employee;
import com.example.ems.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Constructor-based Dependency Injection (Best practice in Spring Boot 3.5)
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Save a new employee
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

   // Update an existing employee
   @Transactional
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepository.findById(id).map(employee -> {
            if (updatedEmployee.getName() != null) {
                employee.setName(updatedEmployee.getName());
            }
            if (updatedEmployee.getEmail() != null) {
                employee.setEmail(updatedEmployee.getEmail());
            }
            if (updatedEmployee.getDepartment() != null) {
                employee.setDepartment(updatedEmployee.getDepartment());
            }
            return employeeRepository.save(employee);
        }).orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
    }


    // Delete an employee
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

package com.example.springSecurityDemo2.controller;

import com.example.springSecurityDemo2.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {
    List<Employee> employees = new ArrayList<>(List.of(
            new Employee(1,"Goutam Dam","Java Developer"),
            new Employee(2,"Arpan Kundu","Java Developer"),
            new Employee(3,"Manojit Das","MERN Developer")));

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employees;
    }

    @PostMapping("/employees")
    public void addEmployee(@RequestBody Employee employee){
        employees.add(employee);
    }

    @GetMapping("/employees/{id}")
    public List<Employee> getEmployeeById(@PathVariable int id){
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .toList();
    }


}

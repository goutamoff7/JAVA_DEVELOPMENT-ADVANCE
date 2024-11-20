package com.goutam.thymeleafDemo.services;

import com.goutam.thymeleafDemo.models.Employee;
import com.goutam.thymeleafDemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Service
public class EmployeeService
{
    @Autowired
    EmployeeRepository employeeRepository;
    //Create
    public void create(Employee employee)
    {
        employeeRepository.save(employee);
    }

    //Read all
    public List<Employee> readAll()
    {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).get();
    }

    public void updateEmployee(Employee employee)
    {
        employeeRepository.save(employee);
    }
}

package com.goutam.thymeleafDemo.controllers;

import com.goutam.thymeleafDemo.models.Employee;
import com.goutam.thymeleafDemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController
{
    @Autowired
    EmployeeService employeeService;

    private Employee employee = new Employee();

    @GetMapping("/register")
    public ModelAndView getRegistrationForm() {
        String viewName = "register";
        Map<String, Object> model = new HashMap<>();
        employee.setFirstName("John"); // Just to Display in the Form
        employee.setLastName("Doe"); // Just to Display in the Form
        model.put("employeeEntry", employee);
        return new ModelAndView(viewName, model);
    }

    @PostMapping("/register")
    public String submitRegistrationForm(@ModelAttribute Employee employee) {
        employeeService.create(employee);
        return employee.getFullName()+", you are Successfully Registered";
    }

    @GetMapping("/readAll")
    public List<Employee> readAll()
    {
        return employeeService.readAll();
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getEditForm(@PathVariable Integer id)
    {
        String viewName = "update";
        Employee employee = employeeService.getEmployeeById(id);
        Map<String, Object> model = new HashMap<>();
        model.put("employeeUpdate", employee);
        return new ModelAndView(viewName, model);
    }

    @PostMapping("/update")
    public String submitUpdateForm(@ModelAttribute Employee employee) {
        employeeService.updateEmployee(employee);
        return employee.getFullName()+", your data is Successfully Updated";
    }
}

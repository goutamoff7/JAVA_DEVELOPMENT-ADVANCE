package com.example.SpringSecurityDemo.controller;

import com.example.SpringSecurityDemo.model.Student;
import com.example.SpringSecurityDemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    StudentService studentService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/get-all-students")
    public List<Student> getStudents()
    {
        return studentService.getStudents();
    }
}

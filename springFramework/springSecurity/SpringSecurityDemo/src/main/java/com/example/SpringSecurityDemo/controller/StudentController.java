package com.example.SpringSecurityDemo.controller;

import com.example.SpringSecurityDemo.model.Student;
import com.example.SpringSecurityDemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    public class StudentController
    {
        @Autowired
        StudentService studentService;

        @PostMapping("/student")
        public Student addStudent(@RequestBody Student student)
        {
            return studentService.addStudent(student);
        }

        //localhost:8080/student/1
        @GetMapping("/student/{id}")
        public Student getStudent(@PathVariable Integer id)
        {
            return studentService.getStudent(id);
        }
    }

package com.example.SpringSecurityDemo.controller;

import com.example.SpringSecurityDemo.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController
{

    private List<Student> students = new ArrayList<>(Arrays.asList(
                            new Student(24,"Goutam",56),
                            new Student(25,"Arpan",65)
                          ));

    @GetMapping("/students")
    public List<Student> getStudents()
    {
        return students;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken (HttpServletRequest request)
    {
        //"_csrf" is the attribute name which we get by inspect localhost:8080/logout page
        return (CsrfToken)request.getAttribute("_csrf");
    }

//    localhost:8080/students
//{
//    "id": 17,
//    "name": "Bapi",
//    "marks": 75
//}

// use postman to POST data
//{
//    "parameterName": "_csrf",
//    "token": "hfmjfjh54c8sehfghghgg67626hvfdhbxfsgdvfg548g656g",
//    "headerName": "X-CSRF-TOKEN"
//}
//    Auth Type - Basic Auth - provide username password
//    Header key = X-CSRF-TOKEN, value = hfmjfjh54c8sehfghghgg67626hvfdhbxfsgdvfg548g656g.

//every time we go to localhost:8080/csrf-token, we will get a new token and that token
//should be used for POST data

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student)
    {
        students.add(student);
        return student;
    }
}

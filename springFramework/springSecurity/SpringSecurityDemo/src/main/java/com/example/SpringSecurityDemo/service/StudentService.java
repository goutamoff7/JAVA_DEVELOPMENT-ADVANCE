package com.example.SpringSecurityDemo.service;

import com.example.SpringSecurityDemo.model.Student;
import com.example.SpringSecurityDemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;


    public List<Student> getStudents() {
       return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(Integer id) {
        return studentRepository.findById(id).orElseThrow(null);
    }
}
package com.example.SpringSecurityDemo.repository;

import com.example.SpringSecurityDemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}

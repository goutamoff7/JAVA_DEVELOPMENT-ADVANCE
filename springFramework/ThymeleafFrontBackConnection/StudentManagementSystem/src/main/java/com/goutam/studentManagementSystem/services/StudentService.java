package com.goutam.studentManagementSystem.services;

import com.goutam.studentManagementSystem.models.Student;
import com.goutam.studentManagementSystem.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService
{
//    listAllStudents -> Read all the Students
//    saveStudents(Student student) -> Write a student in Database
//    getStudentById(Integer id) -> Student
//    updateStudent(Student student, Integer id) -> update in Database
//    deleteStudentById(Integer id) -> Delete from Database

    @Autowired
    StudentRepository studentRepository;

    public List<Student> listAllStudents()
    {
        return studentRepository.findAll();
    }

    public void saveStudents(Student student)
    {
        studentRepository.save(student);
    }

    public Student getStudentById(Integer id)
    {
        return studentRepository.findById(id).get();
    }

    public void updateStudent(Student student, Integer id)
    {
        Student studentToBeUpdated = getStudentById(id);
        studentToBeUpdated.setName(student.getName());
        studentToBeUpdated.setEmail(student.getEmail());
        studentToBeUpdated.setCourse(student.getCourse());
        saveStudents(studentToBeUpdated);
    }

    public void deleteStudentById(Integer id)
    {
        studentRepository.deleteById(id);
    }



}

package com.example.studentEnrollment.services;

import com.example.studentEnrollment.models.Student;
import com.example.studentEnrollment.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StudentCrudService {
    @Autowired
    StudentRepository studentRepository;

    //Create Student Record
    public void enrollStudent(Student student) {
        studentRepository.save(student);
    }

    //Read all Student Record
    public List<Student> readAllStudent() {
        return studentRepository.findAll();
    }

    //Read Student Record by roll
    public Student readStudent(Integer roll) {
        return studentRepository.findById(roll).orElse(null);
    }

    //Update Student Record
    public void updateStudent(Student student, Integer roll) {
        Student studentToBeUpdated = readStudent(roll);
        if(studentToBeUpdated!=null) {
            studentToBeUpdated.setName(student.getName());
            studentToBeUpdated.setCity(student.getCity());
            studentRepository.save(studentToBeUpdated);
        }
    }

    //Delete Student Record
    public void deleteStudent(Integer roll) {
        studentRepository.deleteById(roll);
    }

    //Upload Profile Picture
    public void uploadImage(Integer roll, MultipartFile image) throws IOException {
        Student student = readStudent(roll);
        if(student != null) {
            student.setImage(image.getBytes());
            studentRepository.save(student);
        }
    }

    //Download Profile Picture
    public byte[] downloadImage(Integer roll) {
        Student student = readStudent(roll);
        if(student != null) {
            return student.getImage();
        }
        return null;
    }
}

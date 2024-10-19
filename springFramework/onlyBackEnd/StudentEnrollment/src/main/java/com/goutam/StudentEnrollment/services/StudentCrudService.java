package com.goutam.StudentEnrollment.services;

import com.goutam.StudentEnrollment.models.Student;
import com.goutam.StudentEnrollment.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StudentCrudService
{
    @Autowired
    StudentRepository studentRepository;

    //Create Student Record
    public void enrollStudent(Student student)
    {
        studentRepository.save(student);
    }

    //Read all Student Record
    public List<Student> readAllStudent()
    {
        return studentRepository.findAll();
    }

    //Read Student Record by roll
    public Student readStudent(Integer roll)
    {
        return studentRepository.findById(roll).get();
    }

    //Update Student Record
    public void updateStudent(Student student, Integer roll)
    {
       Student studentToBeUpdated =readStudent(roll);
       //or Student studentToBeUpdated = studentRepository.findById(roll).get();
       studentToBeUpdated.setName(student.getName());
       studentToBeUpdated.setCity(student.getCity());
        studentRepository.save(studentToBeUpdated);
    }

    //Delete Student Record
    public void deleteStudent(Integer roll)
    {
        Student studentToBeDeleted = readStudent(roll);
        studentRepository.delete(studentToBeDeleted);
    }

    //Upload Profile Picture
    public void uploadImage( Integer roll,MultipartFile file) throws IOException
    {
        Student student = studentRepository.findById(roll).get();
            student.setImage(file.getBytes());
            studentRepository.save(student);
    }

    //Download Profile Picture
    public byte[] downloadImage(Integer roll)
    {
        Student student = studentRepository.findById(roll).get();
            return student.getImage();
    }
}

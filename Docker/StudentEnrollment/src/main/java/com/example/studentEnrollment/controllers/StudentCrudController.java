package com.example.studentEnrollment.controllers;

import com.example.studentEnrollment.models.Student;
import com.example.studentEnrollment.services.StudentCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
public class StudentCrudController {
    @Autowired
    StudentCrudService studentCrudService;

    @PostMapping("/students")
    public void enrollStudent(@RequestBody Student student) {
        studentCrudService.enrollStudent(student);
    }

    @GetMapping("/students")
    public List<Student> readAllStudent() {
        return studentCrudService.readAllStudent();
    }

    @GetMapping("/students/{roll}")
    public Student readStudent(@PathVariable Integer roll) {

        return studentCrudService.readStudent(roll);
    }

    @PutMapping("/students/{roll}")
    public void updateStudent(@RequestBody Student student, @PathVariable Integer roll) {
        studentCrudService.updateStudent(student, roll);
    }

    @DeleteMapping("/students/{roll}")
    public void deleteStudent(@PathVariable Integer roll) {
        studentCrudService.deleteStudent(roll);
    }

    @PostMapping("/students/upload-image/{roll}")
    public void uploadImage(@PathVariable Integer roll, @RequestPart MultipartFile image) throws IOException {
        studentCrudService.uploadImage(roll, image);
    }

    @GetMapping(value = "students/download-image/{roll}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] downloadImage(@PathVariable Integer roll) {
        return studentCrudService.downloadImage(roll);
    }

}

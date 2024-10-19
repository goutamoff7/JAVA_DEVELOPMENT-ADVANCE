package com.goutam.StudentEnrollment.controllers;

import com.goutam.StudentEnrollment.models.Student;
import com.goutam.StudentEnrollment.services.StudentCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class StudentCrudController
{
    @Autowired
    StudentCrudService studentCrudService;
    // Create API Endpoint - POST
    //localhost:8080/enroll
    //body (raw,JSON)
//    {
//        "roll":24,
//        "name":"Goutam Dam",
//        "city":"Bankura",
//        "college":"SETGOI"  // This is optional as default college = "SETGOI"
//    }
    @PostMapping("/enroll")
    public void enrollStudent(@RequestBody Student student)
    {
        studentCrudService.enrollStudent(student);
    }

    // Read API Endpoint - GET
    // localhost:8080/readAll
    @GetMapping("/readAll")
    public List<Student> readAllStudent()
    {
       return studentCrudService.readAllStudent();
    }

    //Read API Endpoint - GET
    //localhost:8080/read?roll=24
    @GetMapping("/read")
    public Student readStudent(@RequestParam Integer roll)
    {

        return studentCrudService.readStudent(roll);
    }

    //Update API Endpoint - PUT
    //localhost:8080/update/24
    //body (raw,JSON)
//    {
//        "roll":24,
//        "name":"Goutam Dam",
//        "city":"Barjora"
//    }
    @PutMapping("/update/{roll}")
    public void updateStudent(@RequestBody Student student, @PathVariable Integer roll)
    {
        studentCrudService.updateStudent(student,roll);
    }

    //Delete API Endpoint - DELETE
    //localhost:8080/delete?roll=24
    @DeleteMapping("/delete")
    public void deleteStudent(@RequestParam Integer roll)
    {
        studentCrudService.deleteStudent(roll);
    }

    //Upload image API EndPoint - POST
    //localhost:8080/uploadImage/24
    //choose the file from Directory
    @PostMapping("/uploadImage/{roll}")
    public void uploadImage(@PathVariable Integer roll, @RequestParam("image") MultipartFile file) throws IOException
    {
            studentCrudService.uploadImage(roll,file);
    }

    //Download image API EndPoint - GET
    //localhost:8080/downloadImage/24
    @GetMapping(value="downloadImage/{roll}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] downloadImage(@PathVariable Integer roll) {
        return studentCrudService.downloadImage(roll);
    }


}

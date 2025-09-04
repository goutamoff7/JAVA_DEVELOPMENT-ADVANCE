package com.goutam.studentManagementSystem.controllers;

import com.goutam.studentManagementSystem.models.Student;
import com.goutam.studentManagementSystem.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StudentController
{
    @Autowired
    StudentService studentService;

    //localhost:8080/students
//    @GetMapping("/students")
//    public String listStudents(Model model)
//    {
//        model.addAttribute("students",studentService.listAllStudents());
//        return "students";
//
//    }
    @GetMapping("/students")
    public ModelAndView studentList()
    {
        String viewName="students";
        Map<String,Object> model = new HashMap<>();
        model.put("students",studentService.listAllStudents());
        return new ModelAndView("students",model);
    }
    @GetMapping("/students/new")
    public ModelAndView getStudentForm()
    {
        String viewName="students_create";
        Map<String,Object> model = new HashMap<>();
        model.put("student",new Student());
        return new ModelAndView(viewName,model);
    }

    @PostMapping("/students/new")
    public ModelAndView submitStudentForm(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("students_create");
        }
        studentService.saveStudents(student);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/students");
        return new ModelAndView(redirectView);

//        return studentList();
    }

    @GetMapping("/students/edit/{id}")
    public ModelAndView getUpdateForm(@PathVariable Integer id)
    {
        String viewName="students_update";
        Student student = studentService.getStudentById(id);
        Map<String,Object> model = new HashMap<>();
        model.put("student",student);
        return new ModelAndView(viewName,model);
    }

    @PostMapping("/students/edit/{id}")
    public ModelAndView submitUpdateForm(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("students_update");
        }
        studentService.updateStudent(student,student.getId());

        //Both way can redirect to ("/students")

//        RedirectView redirectView = new RedirectView();
//        redirectView.setUrl("/students");
//        return new ModelAndView(redirectView);

        return studentList();
    }

    @GetMapping("/students/delete/{id}")
    public ModelAndView deleteStudent(@PathVariable Integer id)
    {
        studentService.deleteStudentById(id);
//        RedirectView redirectView = new RedirectView();
//        redirectView.setUrl("/students");
//        return new ModelAndView(redirectView);

        return studentList();
    }
}

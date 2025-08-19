package com.example.springBootJSP.controller;

import com.example.springBootJSP.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        System.out.println("home Method called");
        return "index";
    }

    // Using ModelAndView
//    @RequestMapping("/add-student")
//    public ModelAndView add(@RequestParam("sId") int id,@RequestParam("sName") String name,
//                            ModelAndView modelAndView) {
//        Student student = new Student();
//        student.setsId(id);
//        student.setsName(name);
//        modelAndView.addObject("student",student); // this "result" string should be used in result.jsp
//        modelAndView.setViewName("result"); // result.jsp is the actual view page
//        return modelAndView;
//    }

    // Using ModelAttribute with different variable name
//    @RequestMapping("/add-student")
//    public String add(@ModelAttribute("std") Student student) { // this "std" string should be used in result.jsp
//        return "result";  // result.jsp is the actual view page
//    }

    // variable name is same as result.jsp page
    @RequestMapping("/add-student")
    public String add(Student student) { // this "student" string should be used in result.jsp
        return "result";  // result.jsp is the actual view page
    }

    // @ModelAttribute can be used on method also to return some dynamic content
    @ModelAttribute("course")
    public String courseName(){
        return "java";
    }




}

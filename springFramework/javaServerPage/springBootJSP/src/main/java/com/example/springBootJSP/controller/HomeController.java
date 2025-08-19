package com.example.springBootJSP.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        System.out.println("home Method called");
        return "index";
        // changed the .jsp folder path and removed .jsp extension
        // need to add prefix and suffix in application.properties
    }

//    @RequestMapping("/add")
//    public String add(HttpServletRequest request, HttpSession session) {
//
//        int num1 = Integer.parseInt(request.getParameter("num1"));
//        int num2 = Integer.parseInt(request.getParameter("num2"));
//        int res = num1 + num2;
//
//        session.setAttribute("result",res); // this "result" string should be used in result.jsp
//
//        return "result";
//    }

    // when the jsp variable name is same as request parameter variable name
//    @RequestMapping("/add")
//    public String add(int num1, int num2, HttpSession session) {
//
//        session.setAttribute("result",num1+num2); // this "result" string should be used in result.jsp
//
//        return "result";
//    }

//    Using @RequestParam and HttpSession
//    @RequestMapping("/add")
//    public String add(@RequestParam("num1") int a,@RequestParam("num2") int b, HttpSession session) {
//        session.setAttribute("result",a+b); // this "result" string should be used in result.jsp
//
//        return "result";
//    }

    // Using Model
//    @RequestMapping("/add")
//    public String add(@RequestParam("num1") int a,@RequestParam("num2") int b, Model model) {
//        model.addAttribute("result",a+b); // this "result" string should be used in result.jsp
//        return "result";
//    }

    // Using ModelAndView
    @RequestMapping("/add")
    public ModelAndView add(@RequestParam("num1") int a,@RequestParam("num2") int b, ModelAndView modelAndView) {
        modelAndView.addObject("result",a+b); // this "result" string should be used in result.jsp
        modelAndView.setViewName("result"); // result.jsp is the actual view page
        return modelAndView;
    }




}

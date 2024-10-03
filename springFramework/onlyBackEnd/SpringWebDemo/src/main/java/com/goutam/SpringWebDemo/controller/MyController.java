package com.goutam.SpringWebDemo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class MyController
{
    //localhost:8080   --> Hello, This is My First Spring Web Application
    @GetMapping
    public String homePage()
    {
        return "Hello, This is My First Spring Web Application";
    }
    //localhost:8080/login   --> This is the Login Page
    @GetMapping("login")
    public String loginPage()
    {
        return "This is the Login Page";
    }

    //localhost:8080/search1?query=anything  --> anything
    @GetMapping("search1")
    public String searchPage1(@RequestParam(name="query") String value )
    {
        return value;
    }
//localhost:8080/search2/anything  --> anything
    @GetMapping("search2/{query}")
    public String searchPage2(@PathVariable(name="query") String value )
    {
        return value;
    }

    //localhost:8080/search3/anything  --> anything
    @GetMapping("search3/{value}")
    public String searchPage3(@PathVariable String value )
    {
        return value;
    }
    //localhost:8080/post
    @PostMapping(path="post")
    public void doPost()
    {
        System.out.println("doPost called");
    }

}

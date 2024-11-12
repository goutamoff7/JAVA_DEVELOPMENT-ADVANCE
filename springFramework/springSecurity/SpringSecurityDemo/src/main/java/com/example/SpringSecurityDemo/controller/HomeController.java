package com.example.SpringSecurityDemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController
{
    //localhost:8080
    @GetMapping
    public String greeting(HttpServletRequest request)
    {
        return "This is Spring Security Demo Project"
                +"Session ID : "+ request.getRequestedSessionId()
                +"Session ID : "+request.getSession().getId();
    }
    //In this Project SpringSecurity dependency is added so by default
    // it will generate a login page and also a new password every time we restarted the server
    //userName = user

    // to set our own userName and Password we have to add
    //spring.security.user.name=goutam
    //spring.security.user.password=password
    //in the application.properties file
    //now Spring will not generate a new password
}

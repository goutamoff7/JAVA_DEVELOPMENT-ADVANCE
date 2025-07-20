package com.goutam.simpleWebApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController
{
    @RequestMapping("/login")
    String loginPage()
    {
        return "Welcome to Login Page....";
    }

    @RequestMapping("/about")
    String aboutPage()
    {
        return "Try to build a Simple Web App";
    }
}

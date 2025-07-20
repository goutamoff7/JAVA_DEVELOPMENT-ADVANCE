package com.goutam.simpleWebApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController
{
    @RequestMapping("/")
    @ResponseBody
    public String greet()
    {
        return "Hello Goutam, Welcome to Spring Web";
    }

    @RequestMapping("/signup")
    @ResponseBody
    public String signupPage()
    {
        return "Sign up Here";
    }
}

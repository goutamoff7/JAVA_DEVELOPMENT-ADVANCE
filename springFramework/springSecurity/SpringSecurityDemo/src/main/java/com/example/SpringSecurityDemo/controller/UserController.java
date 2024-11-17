package com.example.SpringSecurityDemo.controller;

import com.example.SpringSecurityDemo.model.Users;
import com.example.SpringSecurityDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class UserController
{
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Users register(@RequestBody Users user)
    {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user)
    {
        return userService.verify(user);
    }
}

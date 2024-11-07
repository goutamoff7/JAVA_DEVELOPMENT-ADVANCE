package com.example.eCommerce.controller;

import com.example.eCommerce.model.Users;
import com.example.eCommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
    @Autowired
    UserService userService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    @PostMapping("/register")
    public Users register(@RequestBody Users user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
        return userService.register(user);
    }
}

package com.example.springSecurityDemo2.controller;

import com.example.springSecurityDemo2.model.Users;
import com.example.springSecurityDemo2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@AllArgsConstructor
@RestController
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return userService.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return userService.verify(user);
    }


}

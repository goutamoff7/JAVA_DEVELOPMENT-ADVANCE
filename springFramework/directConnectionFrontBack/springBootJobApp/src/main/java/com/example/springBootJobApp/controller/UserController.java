package com.example.springBootJobApp.controller;

import com.example.springBootJobApp.model.Users;
import com.example.springBootJobApp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@AllArgsConstructor
@RestController
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return userService.save(user);
    }

}

package com.example.springOauth2Demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @GetMapping("/user-info")
    public Map<String,Object> user(@AuthenticationPrincipal OAuth2User user){
        System.out.println(user);
        return user.getAttributes();
    }
}

package com.example.springOauth2Demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController
{
    @GetMapping("/")
    public String welcomePage()
    {
        return "Welcome here to learn SpringOauth2.0";
    }
}

package com.example.eCommerce.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController
{
    @GetMapping
    public String home(HttpServletRequest request)
    {
        return request.getSession().getId();
    }
}

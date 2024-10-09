package com.goutam.journalApp.controller;

import com.goutam.journalApp.model.User;
import com.goutam.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "ok";
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            if(user.getUsername()!=null && !user.getUsername().isEmpty() &&
            user.getPassword()!=null && !user.getPassword().isEmpty())
                return new ResponseEntity<>(userService.createNewUser(user), HttpStatus.CREATED);
            else
                throw new Exception();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

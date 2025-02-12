package com.goutam.journalApp.controller;

import com.goutam.journalApp.model.User;
import com.goutam.journalApp.DTO.UserDTO;
import com.goutam.journalApp.service.EmailService;;
import com.goutam.journalApp.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Tag(name = "Public APIs", description = "User Signup and Login")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "ok";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDTO userDTO) {
        try {
            if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty() &&
                    userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                User user = new User();
                user.setEmail(userDTO.getEmail());
                user.setPassword(userDTO.getPassword());
                User newUser = userService.createNewUser(user);
                emailService.sendEmail(user.getEmail());
                return new ResponseEntity<>(newUser, HttpStatus.CREATED);
            } else
                return new ResponseEntity<>("User Registration Failed!!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        try {
            User user = new User();
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            return new ResponseEntity<>(userService.verify(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

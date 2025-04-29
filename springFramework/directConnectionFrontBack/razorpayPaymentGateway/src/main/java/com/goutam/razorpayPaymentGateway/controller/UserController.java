package com.goutam.razorpayPaymentGateway.controller;

import com.goutam.razorpayPaymentGateway.model.User;
import com.goutam.razorpayPaymentGateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try{
            User savedUser = userService.saveUser(user);
            if(savedUser!=null)
                return new ResponseEntity<>(savedUser, HttpStatus.OK);
            return new ResponseEntity<>("Failed to save User", HttpStatus.SERVICE_UNAVAILABLE);
        }catch(Exception e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>("Something Went Wrong!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

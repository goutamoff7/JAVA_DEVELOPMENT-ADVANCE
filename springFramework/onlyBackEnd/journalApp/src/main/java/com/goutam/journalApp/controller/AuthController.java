package com.goutam.journalApp.controller;

import com.goutam.journalApp.model.User;
import com.goutam.journalApp.service.EmailService;
import com.goutam.journalApp.service.JwtService;
import com.goutam.journalApp.service.UserDetailsServiceImpl;
import com.goutam.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AuthController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @Autowired
    EmailService emailService;

    @GetMapping("/oauth2")
    public ResponseEntity<?> oauthLogin(@AuthenticationPrincipal OAuth2User user) {
        if (user == null) {
            return new ResponseEntity<>("User is not authenticated",HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = null;
        String email = user.getAttribute("email");
        String username = email.split("@")[0];   // username = abc123 when email = abc12@gmail.com
        try {
            userDetails = userDetailsService.loadUserByUsername(username);
        } catch (Exception e) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
            userService.createNewUser(newUser);
            emailService.sendEmail(email);
        }
        String jwtToken = jwtService.generateToken(username);
        Map<String,Object> response = new HashMap<>();
        response.put("name",user.getAttribute("name"));
        response.put("email",user.getAttribute("email"));
        response.put("picture",user.getAttribute("picture"));
        response.put("jwt_token",jwtToken);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
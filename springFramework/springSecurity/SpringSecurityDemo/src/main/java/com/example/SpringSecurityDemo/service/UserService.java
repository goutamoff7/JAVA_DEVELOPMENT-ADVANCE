package com.example.SpringSecurityDemo.service;

import com.example.SpringSecurityDemo.config.SecurityConfiguration;
import com.example.SpringSecurityDemo.model.Role;
import com.example.SpringSecurityDemo.model.Users;
import com.example.SpringSecurityDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService
{
    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository usersRepository;


    public Users register(Users user)
    {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
//        user.setPassword(encoder.encode(user.getPassword()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        return usersRepository.save(user);
    }

    public String verify(Users user)
    {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(),
                                user.getPassword()
                        )
                );
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());
        else
            return "Login Failed";
    }
}

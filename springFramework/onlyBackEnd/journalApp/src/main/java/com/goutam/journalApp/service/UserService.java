package com.goutam.journalApp.service;

import com.goutam.journalApp.model.JournalEntry;
import com.goutam.journalApp.model.Roles;
import com.goutam.journalApp.model.User;
import com.goutam.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

//    @CachePut(value="user",key="#result.id.toString()")
    public User createNewUser(User user) {
        user.setUsername(extractUsername(user));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Roles.ROLE_USER);
        return userRepository.save(user);
    }

//    @CachePut(value="user",key="#result.id.toString()")
    public void saveUser(User user) throws Exception {
        userRepository.save(user);
    }

//    @Cacheable(value="user",key="#username")
    public User getUserByUsername(String username) throws Exception {
        return userRepository.findUserByUsername(username);
    }

//    @CacheEvict(value="user",key="#username")
    public void deleteUser(String username) throws Exception {
        User user = getUserByUsername(username);
        userRepository.deleteById(user.getId());
    }

//    @CacheEvict(value="user",key="#username")
    public void updateUserPassword(String username, String password) throws Exception {
        User user = getUserByUsername(username);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(password));
            saveUser(user);
        } else
            throw new Exception();
    }

    public String verify(User user) {
        String username = extractUsername(user);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        username, user.getPassword()));
        if (authentication.isAuthenticated())
            return jwtService.generateToken(username);
        return "Login Failed";
    }

    public String extractUsername(User user) {
        return user.getEmail().split("@")[0];
    }
}

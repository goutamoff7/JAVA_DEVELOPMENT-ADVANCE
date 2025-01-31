package com.goutam.journalApp.service;

import com.goutam.journalApp.model.JournalEntry;
import com.goutam.journalApp.model.User;
import com.goutam.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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

    public User createNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        return userRepository.save(user);
    }

    public void saveUser(User user) throws Exception
    {
        userRepository.save(user);
    }


    public User getUserByUsername(String username) throws Exception{
        return userRepository.findUserByUsername(username);
    }

    public void deleteUser(String username) throws Exception {
        User user = getUserByUsername(username);
        userRepository.deleteById(user.getId());
    }

    public User updateUser(String username,User updatedUser) throws Exception {
        User oldUser = getUserByUsername(username);
        if(oldUser!=null) {
            oldUser.
                    setPassword(updatedUser.getPassword() != null && !updatedUser.getPassword().equals("") ?
                            updatedUser.getPassword() : oldUser.getPassword());

            return createNewUser(oldUser);
        }
        else
            throw new Exception();
    }

    public String verify(User user)
    {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());
        else
            return "Login Failed";
    }
}

package com.goutam.journalApp.service;

import com.goutam.journalApp.model.JournalEntry;
import com.goutam.journalApp.model.Roles;
import com.goutam.journalApp.model.User;
import com.goutam.journalApp.repository.JournalEntryRepository;
import com.goutam.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JournalEntryRepository journalEntryRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User createNewAdmin(User user) {
        user.setUsername(userService.extractUsername(user));
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Roles.ROLE_ADMIN);
        return userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public List<JournalEntry> getAllJournals() {
        return journalEntryRepository.findAll();
    }
}

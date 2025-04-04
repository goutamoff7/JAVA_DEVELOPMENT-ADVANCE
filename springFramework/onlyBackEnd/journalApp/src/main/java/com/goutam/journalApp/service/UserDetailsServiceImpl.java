package com.goutam.journalApp.service;

import com.goutam.journalApp.model.PrincipleUser;
import com.goutam.journalApp.model.User;
import com.goutam.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user==null) {
            throw new UsernameNotFoundException("User not Found");
        }
        return new PrincipleUser(user);
    }
}
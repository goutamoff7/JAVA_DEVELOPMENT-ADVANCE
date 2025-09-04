package com.example.springBootJobApp.service;

import com.example.springBootJobApp.model.UserPrinciple;
import com.example.springBootJobApp.model.Users;
import com.example.springBootJobApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("User Not Found");
        return new UserPrinciple(user);
    }
}

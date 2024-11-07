package com.example.eCommerce.service;

import com.example.eCommerce.model.PrincipalUser;
import com.example.eCommerce.model.Users;
import com.example.eCommerce.repository.ProductRepository;
import com.example.eCommerce.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService
{
    @Autowired
    UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Users user = usersRepository.findByUsername(username);
        if(user==null)
            throw new UsernameNotFoundException("User Not Found");
        return new PrincipalUser(user);
    }
}

package com.example.eCommerce.service;

import com.example.eCommerce.model.Users;
import com.example.eCommerce.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    UsersRepository usersRepository;


    public Users register(Users user)
    {
        return usersRepository.save(user);

    }
}

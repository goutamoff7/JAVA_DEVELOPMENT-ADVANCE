package com.example.SpringSecurityDemo.repository;

import com.example.SpringSecurityDemo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Integer>
{

    Users findByUsername(String username);
}

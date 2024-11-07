package com.example.eCommerce.repository;

import com.example.eCommerce.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Integer>
{

    Users findByUsername(String username);
}

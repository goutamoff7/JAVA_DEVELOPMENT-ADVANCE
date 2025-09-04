package com.example.springBootJobApp.model;

import com.example.springBootJobApp.constants.Role;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}

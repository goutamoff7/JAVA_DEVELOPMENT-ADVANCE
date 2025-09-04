package com.example.SpringSecurityDemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message="Username must not blank")
    @Column(nullable = false, unique = true)
    @Size(min=3,max=10,message = "Username must between 3 to 10 characters")
    private String username;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$"
    ,message = "At least one lowercase letter (?=.*[a-z]).\n" +
            "    At least one uppercase letter (?=.*[A-Z]).\n" +
            "    At least one special character (?=.*[@#$%^&+=]).\n" +
            "    Minimum length of 8 characters.\n" +
            "    At least one digit (?=.*[0-9]).")
    private String password;

    @NotBlank
    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    ,message = "Please enter a valid Email")
//    The local part before @ supports alphanumeric characters, dots, and special characters like _, %, +, -.
//    The domain part after @ supports alphanumeric characters and dots,
//    followed by a top-level domain of 2-6 characters (e.g., .com, .org).
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Role role;
}

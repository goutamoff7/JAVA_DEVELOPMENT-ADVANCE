package com.goutam.journalApp.model;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Hidden
public class UserDTO {
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
}

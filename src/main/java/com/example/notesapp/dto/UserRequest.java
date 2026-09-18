package com.example.notesapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message= "Username is required")
    @Size(min= 3, max = 30, message = "Username must be between 3 & 50 characters")
    private String username;

    @NotBlank(message = " Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message="password is required")
    @Size(min=6, message = "Password must contain at least 6 characters")
    private String password;
}

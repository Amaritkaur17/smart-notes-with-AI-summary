package com.example.notesapp.service;

import com.example.notesapp.dto.UserRequest;
import com.example.notesapp.dto.UserResponse;
import com.example.notesapp.entity.User;
import com.example.notesapp.repository.UserRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRespository userRespository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse registerUser(UserRequest request){

        if(userRespository.existsByUsername(request.getUsername())){
           throw new RuntimeException("Username already exsists");
        }
        if(userRespository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exsists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRespository.save(user);

        return UserResponse.builder().id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .build();

    }
}

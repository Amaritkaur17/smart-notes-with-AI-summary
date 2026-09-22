package com.example.notesapp.service;

import com.example.notesapp.dto.LoginRequest;
import com.example.notesapp.dto.LoginResponse;
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

    private final UserRespository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public UserResponse registerUser(UserRequest request){

        if(userRepository.existsByUsername(request.getUsername())){
           throw new RuntimeException("Username already exsists");
        }
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exsists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);

        return UserResponse.builder().id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .build();

    }

    public LoginResponse loginUser(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->
                        new RuntimeException("Invalid email or password"));
        if( !passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getUsername());
        return LoginResponse.builder()
                .token(token)
                .build();
    }
}

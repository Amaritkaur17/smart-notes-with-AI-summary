package com.example.notesapp.service;

import com.example.notesapp.entity.User;
import com.example.notesapp.repository.UserRespository;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.Collections;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import static org.springframework.security.core.userdetails.User.builder;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

    private final UserRespository userRespository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRespository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found: "+ username));
        
        return builder()
                .username(user.getUsername())
                .password(user.getPassword())  
                .authorities(Collections.emptyList())
                .build();
    }



}

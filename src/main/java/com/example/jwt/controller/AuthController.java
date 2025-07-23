package com.example.jwt.controller;

import com.example.jwt.entity.User;
import com.example.jwt.repository.UserRepository;
import com.example.jwt.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserRepository repo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        // WALIDACJA DANYCH
         if (user.getUsername() == null || user.getPassword() == null) {
           throw new IllegalArgumentException("Username and password must not be null");
        }
        // ZABEZPIECZENIE PRZED DUPLIKATEM
        if (repo.findByUsername(user.getUsername()).isPresent()) {
          throw new RuntimeException("Username already exists");
        } 
        //ZAPIS NOWEGO UŻYTKOWNIKA
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        repo.save(user);
        return "User registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        // WALIDACJA DANYCH
       if (user.getUsername() == null || user.getPassword() == null) {
          throw new IllegalArgumentException("Username and password must not be null");
       }
        // UWIERZYTELNIENIE
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        return jwtUtil.generateToken(user.getUsername());
    }
}
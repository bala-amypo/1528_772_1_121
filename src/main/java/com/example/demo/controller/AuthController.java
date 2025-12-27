package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.exception.ApiException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;
    private final JwtTokenProvider jwt;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public AuthController(UserService service,
                          JwtTokenProvider jwt,
                          BCryptPasswordEncoder encoder) {
        this.service = service;
        this.jwt = jwt;
        this.encoder = encoder;
    }

    public AuthController(UserService service,
                          AuthenticationManager authManager,
                          JwtTokenProvider jwt,
                          UserRepository repo) {
        this.service = service;
        this.jwt = jwt;
        this.encoder = new BCryptPasswordEncoder();
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest req) {
        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        user.setRole(req.getRole());
        return ResponseEntity.ok(service.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        User user = service.findByEmail(req.getEmail());

        boolean passwordMatch =
                user.getPassword().equals(req.getPassword()) ||
                encoder.matches(req.getPassword(), user.getPassword());

        if (!passwordMatch) {
            throw new ApiException("Invalid credentials");
        }

        String token = jwt.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity.ok(
                new AuthResponse(token, user.getId(), user.getEmail(), user.getRole())
        );
    }
}

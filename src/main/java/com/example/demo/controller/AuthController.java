package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.exception.ApiException;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;
    private final JwtTokenProvider jwt;
    private final BCryptPasswordEncoder encoder;

    public AuthController(UserService service,
                          JwtTokenProvider jwt,
                          BCryptPasswordEncoder encoder) {
        this.service = service;
        this.jwt = jwt;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest req) {
        User u = new User();
        u.setName(req.getName());
        u.setEmail(req.getEmail());
        u.setPassword(req.getPassword());
        u.setRole(req.getRole());
        return service.register(u);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        User u = service.findByEmail(req.getEmail());
        if (!encoder.matches(req.getPassword(), u.getPassword())) {
            throw new ApiException("Invalid credentials");
        }
        String token = jwt.generateToken(u.getId(), u.getEmail(), u.getRole());
        return new AuthResponse(token, u.getId(), u.getEmail(), u.getRole());
    }
}

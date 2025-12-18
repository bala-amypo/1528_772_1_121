package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public interface UserService  {
    public User register(User user);
    public Optional<User> findbyEmail(String email);
}
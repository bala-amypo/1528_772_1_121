package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public User registerUser(User user){
        return userService.register(user);
    }

    @PostMapping("/findbyemail")
    public Optional<User> findEmail(String email){
        return userService.findbyemail(email);
    }
}

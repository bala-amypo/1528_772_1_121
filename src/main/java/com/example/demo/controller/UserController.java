package com.example.demo.controller;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
@RestController
public class UserController {
    @Autowired
    UserService us;
    @PostMapping("/register")
    public User add(@RequestBody User user)
    {
        return us.register(user);
    }
    @PostMapping("/login")
    public Optional<User> find(@RequestBody String email)
    {
        return us.findbyemail(email);
    }
    
}

package com.example.demo.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository us;
    @Override
    public User register(User user)
    {
        return us.save(user);
    }
    public Optional<User> findbyemail(String email)
    {
        return us.findByEmail(email);
    }
    
}
package com.example.demo.service;

import com.example.demo.model.User;
package com.example.demo.service;

@Service
public interface UserService {

    User register(User user);

    User findByEmail(String email);
}

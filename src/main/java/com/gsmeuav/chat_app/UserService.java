package com.gsmeuav.chat_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    User Register(String username, String password) {
        User user = new User();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordHash = encoder.encode(password);

        user.setUsername(username);
        user.setPasswordHash(passwordHash);

        user.setCreatedAt(Date.valueOf(
                java.time.LocalDate.now()
        ));

        return userRepository.save(user);
    }

    User Login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(password, user.getPasswordHash())) {
            return user;
        } else {
            return null;
        }
    }

    List<User> getAllUsers(){
        return userRepository.findAll();
    }

}

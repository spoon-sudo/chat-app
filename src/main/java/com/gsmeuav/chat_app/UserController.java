package com.gsmeuav.chat_app;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService ;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/auth/register")
    public User register(@RequestParam String username, @RequestParam String password) {
        return userService.Register(username, password);
    }

    @PostMapping("/auth/login")
    public User login(@RequestParam String username, @RequestParam String password) {
        return userService.Login(username, password);
    }
}

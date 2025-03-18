package com.vlasov.calories.controllers;

import com.vlasov.calories.entities.User;
import com.vlasov.calories.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public HttpEntity<Object> createUser(@RequestBody User user) {
        return userService.createResponse(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
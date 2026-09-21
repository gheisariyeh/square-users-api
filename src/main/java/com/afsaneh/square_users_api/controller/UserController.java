package com.afsaneh.square_users_api.controller;

import com.afsaneh.square_users_api.entity.User;
import com.afsaneh.square_users_api.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping ("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public User createUser(User user) {
        return userService.createUser();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable String id) {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteById(id);
    }

    @GetMapping("/{id}/valid")
    public boolean isValidUser(@PathVariable String id) {
        return userService.isValidUser(id);
    }

}

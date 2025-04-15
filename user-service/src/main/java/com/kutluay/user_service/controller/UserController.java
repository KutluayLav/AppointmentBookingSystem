package com.kutluay.user_service.controller;


import com.kutluay.user_service.exception.UserException;
import com.kutluay.user_service.model.User;
import com.kutluay.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/createUser")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers() throws UserException {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/api/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long id) throws Exception {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        String user = userService.deleteUser(id);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User user) throws Exception {
         User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}

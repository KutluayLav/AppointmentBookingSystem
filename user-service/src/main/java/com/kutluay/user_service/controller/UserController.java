package com.kutluay.user_service.controller;


import com.kutluay.user_service.model.User;
import com.kutluay.user_service.service.UserService;
import jakarta.validation.Valid;
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
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/api/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long id) throws Exception {
        return ResponseEntity.ok(userService.findById(id));
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
    @PutMapping("/api/users/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.updateUser(id,user));
    }
}

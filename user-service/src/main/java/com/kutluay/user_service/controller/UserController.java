package com.kutluay.user_service.controller;


import com.kutluay.user_service.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @GetMapping("/api/users")
    public User getUser(){
        User user = new User();
        user.setEmail("kutluay@gmail.com");
        user.setFullName("kutluay ulutas");
        user.setPhone("29013980219830");
        user.setRole("customer");
        return user;
    }
}

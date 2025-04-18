package com.kutluayulutas.booking.service.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String HomeControllerHandler(){
            return "Booking Microservice for salon booking system";
    }
}

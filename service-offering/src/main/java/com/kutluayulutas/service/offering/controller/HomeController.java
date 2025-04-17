package com.kutluayulutas.service.offering.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String HomeControllerHandler(){
        return "Service Offering Microservice for salon booking system";
    }
}

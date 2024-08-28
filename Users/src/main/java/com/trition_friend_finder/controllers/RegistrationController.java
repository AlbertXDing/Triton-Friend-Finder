package com.trition_friend_finder.controllers;

import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trition_friend_finder.models.Registration;
import com.trition_friend_finder.services.RegistrationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    
    private  RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody Registration request) {
        return registrationService.register(request);
    }
}

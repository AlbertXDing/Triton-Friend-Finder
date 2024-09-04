package com.trition_friend_finder.services;

import org.springframework.stereotype.Service;

import com.trition_friend_finder.UserRole;
import com.trition_friend_finder.models.Registration;
import com.trition_friend_finder.models.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidatorService emailValidator;
    public String register(Registration request) {
        boolean validEmail = emailValidator.test(request.getEmail());
        if (!validEmail) {
            throw new IllegalStateException("Invalid Email");
        }
        return userService.signUpUser(
            new User(request.getUsername(), 
            request.getPassword(), 
            request.getEmail(), 
            request.getFirstname(), 
            request.getLastname(), 
            request.getSex(),
            UserRole.USER) 
        );
    }
}
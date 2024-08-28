package com.trition_friend_finder.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication; 

import com.trition_friend_finder.models.User;
import com.trition_friend_finder.response.AuthResponse;
import com.trition_friend_finder.security.config.JwtProvider;
import com.trition_friend_finder.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<List<User>>(userService.allUsers(),HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Optional<User>> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.singleUser(username),HttpStatus.OK);
    }

    /**
     * Handles user signin by authenticating the provided credentials and returning a JSON Web Token (JWT) on success.
     * 
     * Processes the signin request by extracting the username and password from provided request body.
     * Authenticates the credentials using the authenticate method and sets the authentication context.
     * On successful authentication, it generates a JWT using the JwtProvider and creates an AuthResponse object containing the JWT and a success message.
     * 
     * @param loginRequest  The user's signin credentials, containing the email and password.
     * @return              A ResponseEntity containing an AuthResponse object with the JWT and a success message, indicating a successful signin.
     */
    @PostMapping("/signin") 
    public ResponseEntity<AuthResponse> signin(@RequestBody User loginRequest) { 
        String username = loginRequest.getEmail(); 
        String password = loginRequest.getPassword(); 
      
        System.out.println(username+"-------"+password); 
      
        // Authenticate the username and password
        Authentication authentication = authenticate(username,password); 
        // Set the authentication context
        SecurityContextHolder.getContext().setAuthentication(authentication); 
      
        // Generate a JWT based on the authentication
        String token = JwtProvider.generateToken(authentication); 
        // Create a new AuthResponse object
        AuthResponse authResponse = new AuthResponse(); 
      
        // Set the message of the AuthResponse to "Login success"
        authResponse.setMessage("Login success"); 
        // Set the JWT of the AuthResponse to the generated token
        authResponse.setJwt(token); 
        // Set the status of the AuthResponse to true
        authResponse.setStatus(true); 

        /*
         * auth response will look like this:
         * {"jwt":"eyJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE3MjQ4NzU2MzAsImV4cCI6MTcyNDk2MjAzMCwiZW1haWwiOiJqb2huZG9lMyIsImF1dGhvcml0aWVzIjoiVVNFUiJ9.iHXzSQf-brUFbSZvlEn0iZorgwDeD5YHRL95RLBRS8_1dgP72z3w8oORtIVnasbn","message":"Login success","status":true}
         */
      
        // Return a ResponseEntity with the AuthResponse and a HTTP status of OK
        return new ResponseEntity<>(authResponse,HttpStatus.OK);
    }

    /**
     * Authenticates a user based on the provided username and password.
     * 
     * This method checks the existence of the user in the system and verifies the password.
     * If the credentials are valid, it returns an authentication object; otherwise, it throws a BadCredentialsException.
     * 
     * @param username  the username to authenticate
     * @param password  the password to authenticate
     * @return          the authentication object if the credentials are valid
     * @throws BadCredentialsException if the username or password is invalid
     */
    private Authentication authenticate(String username, String password) { 
  
        System.out.println(username+"---++----"+password); 
  
        UserDetails userDetails = userService.loadUserByUsername(username); 
  
        System.out.println("Sig in in user details"+ userDetails); 
        
        // Check if user exists
        if(userDetails == null) { 
            System.out.println("Sign in details - null" + userDetails); 
  
            throw new BadCredentialsException("Invalid username and password"); 
        } 
        // If username exists then check if passward matches input
        if(!passwordEncoder.matches(password,userDetails.getPassword())) { 
            System.out.println("Sign in userDetails - password mismatch"+userDetails); 
  
            throw new BadCredentialsException("Invalid password"); 
  
        } 

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities()); 
  
    }
}

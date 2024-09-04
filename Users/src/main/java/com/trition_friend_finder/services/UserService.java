package com.trition_friend_finder.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trition_friend_finder.models.User;
import com.trition_friend_finder.repositories.UserRepository;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{
    @Autowired
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> allUsers() {
        return userRepository.findAll();
    }
    
    public Optional<User> singleUser(String username) {
        return userRepository.findUserByUsername(username);
    }

    /**
     * Loads a user by their email address.
     *
     * @param email    the email address of the user to load
     * @return         the loaded user, or throws an exception if not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("User with email %s not found", email)));
    }

    /**
     * refactored from create user
     * 
     * before signing up, it will check if credentials of user already exists -> throw new exception if true.
     * Use bCryptPasswordEncoder to encode password so it can be stored as a token in database.
     * @param user  User object to be created
     * @return
     */
    public String signUpUser(User user) {
        boolean userExists = userRepository.findUserByEmail(user.getEmail()).isPresent();

        if (userExists) {
            throw new IllegalStateException("User with email " + user.getEmail() + " already exists");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);
        return "";
    }
}

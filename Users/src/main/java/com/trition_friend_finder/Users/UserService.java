package com.trition_friend_finder.Users;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> allUsers() {
        return userRepository.findAll();
    }

//    public Optional<User> getUserById(ObjectId id) {
//        return userRepository.findById(id);
//    }

//    public Optional<User> getUserByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
    public Optional<User> singleUser(String username) {
        return userRepository.findUserByUsername(username);
    }
}

package com.trition_friend_finder.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trition_friend_finder.models.User;
import com.trition_friend_finder.repositories.UserRepository;

import java.util.HashSet;
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

    public User createUser(String username, String password, String email,
                           String firstname, String lastName, String sex, HashSet<String> seenUsers) {
        User user = new User(username,password,email,firstname,lastName,sex, seenUsers);
        userRepository.insert(user);
        return user;
    }

    public void addSeenUsers(String username, String seenUser) {
        Optional<User> userFind = userRepository.findUserByUsername(username);
        if(userFind.isPresent()) {
            User user = userFind.get();
            HashSet<String> seenUsersSet = user.getSeenUsers();
            seenUsersSet.add(seenUser);
            user.setSeenUsers(seenUsersSet);
            userRepository.save(user);
        }
    }

    public boolean userSeenBefore(String username, String seenUser) {
        Optional<User> userFind = userRepository.findUserByUsername(username);
        boolean seen = false;
        if(userFind.isPresent()) {
            User user = userFind.get();
            HashSet<String> seenUsersSet = user.getSeenUsers();
            seen = seenUsersSet.contains(seenUser);
        }
        return seen;
    }
}

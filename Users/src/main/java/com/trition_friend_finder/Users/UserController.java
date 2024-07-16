package com.trition_friend_finder.Users;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<List<User>>(userService.allUsers(),HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<User>> getUserById(@PathVariable ObjectId id){
//        return new ResponseEntity<Optional<User>>(userService.getUserById(id),HttpStatus.OK);
//    }

//    @GetMapping("/{username}")
//    public ResponseEntity<Optional<User>> getUserByUsername(@PathVariable String username){
//        return new ResponseEntity<Optional<User>>(userService.getUserByUsername(username),HttpStatus.OK);
//    }

    @GetMapping("/{username}")
    public ResponseEntity<Optional<User>> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.singleUser(username),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody Map<String,String> payload) {
        return new ResponseEntity<>(userService.createUser(payload.get("username"),payload.get("password"),payload.get("email"),
                payload.get("firstName"),payload.get("lastName"),payload.get("sex")), HttpStatus.CREATED);
    }
}

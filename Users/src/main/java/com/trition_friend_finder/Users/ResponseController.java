package com.trition_friend_finder.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/responses")
public class ResponseController {
    @Autowired
    private ResponseService responseService;

    @PostMapping
    public ResponseEntity<Response> createResponse(@RequestBody Map<String, Object> payload) {
        return new ResponseEntity<>(responseService.createResponse((List<String>)payload.get("responses"),
                (String)payload.get("username")), HttpStatus.CREATED);
    }
//    @PostMapping
//    public ResponseEntity<Response> createResponse(@RequestBody Map<String, Object> payload) {
//        return new ResponseEntity<>(responseService.createResponse((String)payload.get("responses"),
//                (String)payload.get("username")), HttpStatus.CREATED);
//    }
}

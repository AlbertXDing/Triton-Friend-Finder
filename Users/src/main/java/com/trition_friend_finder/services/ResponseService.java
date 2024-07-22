package com.trition_friend_finder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.trition_friend_finder.models.Response;
import com.trition_friend_finder.models.User;
import com.trition_friend_finder.repositories.ResponseRepository;

import java.util.List;

@Service
public class ResponseService {
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Response createResponse(List<String> responses, String username) {
        Response response = responseRepository.insert(new Response(responses));

        mongoTemplate.update(User.class)
                .matching(Criteria.where("username").is(username))
                .apply(new Update().push("responseId").value(response)).first();
        return response;
    }
//    public Response createResponse(String responses, String username) {
//        Response response = new Response(responses);
//        responseRepository.insert(response);
//
//        mongoTemplate.update(User.class)
//                .matching(Criteria.where("username").is(username))
//                .apply(new Update().push("responseId").value(response));
//        return response;
//    }
}

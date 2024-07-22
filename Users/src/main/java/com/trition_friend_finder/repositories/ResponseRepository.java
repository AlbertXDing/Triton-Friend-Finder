package com.trition_friend_finder.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.trition_friend_finder.models.Response;

@Repository
public interface ResponseRepository extends MongoRepository<Response, ObjectId> {

}

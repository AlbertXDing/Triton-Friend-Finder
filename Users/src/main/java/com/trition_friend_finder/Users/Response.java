package com.trition_friend_finder.Users;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "responses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    @Id
    private ObjectId id;
    private List<String> responses;

    public Response(List<String> responses) {
        this.responses = responses;
    }
}

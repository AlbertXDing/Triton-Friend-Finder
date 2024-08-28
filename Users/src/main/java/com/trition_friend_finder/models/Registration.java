package com.trition_friend_finder.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Registration {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String sex;
}

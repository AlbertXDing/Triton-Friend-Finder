package com.trition_friend_finder.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.trition_friend_finder.UserRole;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import javax.persistence.Entity;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails{

    @Id
    @DocumentReference
    private ObjectId id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String sex;
    private List<Response> responseId;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private boolean locked = false;
    private boolean enabled = true;
    
    public User(
        String username, 
        String password, 
        String email, 
        String firstName, 
        String lastName, 
        String sex,
        UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.role = role;
    }

    @Override
    public String getUsername() {    
        return username;
    }

    @Override    
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }
}

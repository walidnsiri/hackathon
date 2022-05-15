package com.example.matchmaking.domain.model;


import com.example.matchmaking.domain.enums.UserType;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "users") @Data
public class User implements UserDetails {

    @Id
    private ObjectId id;

    private UserType userType;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @CreatedBy
    private User createdUser;
    @LastModifiedBy
    private User lastModifiedByUser;

    private boolean enabled = true;

    @Indexed(unique=true)
    private String username;

    private String password;

    private Set<Role> authorities = new HashSet<>();


    private Profile profile;

    private Societe societe;

    private List<Event> events;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

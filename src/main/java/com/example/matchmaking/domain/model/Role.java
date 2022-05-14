package com.example.matchmaking.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data @AllArgsConstructor @NoArgsConstructor
public class Role implements GrantedAuthority {

    public static final String USER_ADMIN_EVENTS = "USER_ADMIN_EVENTS";
    public static final String USER_ADMIN_OD = "USER_ADMIN_OD";
    public static final String USER_EVENTS = "USER_EVENTS";
    public static final String USER_OD = "USER_OD";

    private String authority;

}

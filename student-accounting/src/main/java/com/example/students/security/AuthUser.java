package com.example.students.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;


public class AuthUser extends org.springframework.security.core.userdetails.User {

    private final Long id;

    public AuthUser(Long id, String login, String passwordHash) {
        super(login, passwordHash, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

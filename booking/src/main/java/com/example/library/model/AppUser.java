package com.example.library.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class AppUser {
    private Long id;
    private String username;
    private String password;
    private Set<Role> roles = new HashSet<>();
}

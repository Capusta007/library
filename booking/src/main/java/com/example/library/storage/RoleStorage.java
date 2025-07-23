package com.example.library.storage;

import com.example.library.model.Role;

import java.util.Optional;

public interface RoleStorage {
    public Optional<Role> getRoleByName(String name);
}

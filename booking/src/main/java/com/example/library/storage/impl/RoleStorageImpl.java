package com.example.library.storage.impl;

import com.example.library.mapper.RoleMapper;
import com.example.library.model.Role;
import com.example.library.repository.RoleRepository;
import com.example.library.storage.RoleStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleStorageImpl implements RoleStorage {
    private final RoleRepository repository;
    private final RoleMapper mapper;

    @Override
    public Optional<Role> getRoleByName(String name) {
        return repository.findByName(name).map(mapper::map);
    }
}

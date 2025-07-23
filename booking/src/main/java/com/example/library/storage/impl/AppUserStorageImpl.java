package com.example.library.storage.impl;

import com.example.library.entity.AppUserEntity;
import com.example.library.mapper.AppUserMapper;
import com.example.library.model.AppUser;
import com.example.library.repository.AppUserRepository;
import com.example.library.storage.AppUserStorage;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AppUserStorageImpl implements AppUserStorage {
    private final AppUserRepository repository;
    private final AppUserMapper mapper;

    @Override
    public AppUser createUser(AppUser user) throws EntityExistsException {
        if (repository.existsByUsername(user.getUsername())) {
            throw new EntityExistsException("User with username " + user.getUsername() + " already exists");
        }

        AppUserEntity userEntity = repository.save(mapper.toEntity(user));
        return mapper.map(userEntity);
    }

    @Override
    public Optional<AppUser> getUserByUsername(String username) {
        return repository.findByUsername(username).map(mapper::map);
    }

    @Override
    public boolean existsUser(String username) {
        return repository.existsByUsername(username);
    }


}

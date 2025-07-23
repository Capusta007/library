package com.example.library.storage;

import com.example.library.model.AppUser;
import jakarta.persistence.EntityExistsException;

import java.util.Optional;

public interface AppUserStorage {
    public AppUser createUser(AppUser user) throws EntityExistsException;

    public Optional<AppUser> getUserByUsername(String username);

    public boolean existsUser(String username);
}

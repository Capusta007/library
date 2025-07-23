package com.example.library.service.impl;

import com.example.library.dto.RegistrationRequestDto;
import com.example.library.mapper.AppUserMapper;
import com.example.library.model.AppUser;
import com.example.library.model.Role;
import com.example.library.service.RegistrationService;
import com.example.library.storage.AppUserStorage;
import com.example.library.storage.RoleStorage;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final AppUserStorage appUserStorage;
    private final RoleStorage roleStorage;
    private final AppUserMapper appUserMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(RegistrationRequestDto dto) throws EntityExistsException {
        if (appUserStorage.existsUser(dto.getUsername())) {
            throw new EntityExistsException("Username {" + dto.getUsername() + "} already exists");
        }
        AppUser user = appUserMapper.map(dto);
        Role role = roleStorage.getRoleByName("USER").orElseThrow(() -> new EntityNotFoundException("Role not found"));
        Set<Role> roles = Set.of(role);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        appUserStorage.createUser(user);
    }
}

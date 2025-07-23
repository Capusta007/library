package com.example.library.service;

import com.example.library.dto.RegistrationRequestDto;
import jakarta.persistence.EntityExistsException;

public interface RegistrationService {
    public void registerUser(RegistrationRequestDto dto) throws EntityExistsException;
}

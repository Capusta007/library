package com.example.library.mapper;

import com.example.library.dto.RegistrationRequestDto;
import com.example.library.entity.AppUserEntity;
import com.example.library.model.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface AppUserMapper {
    AppUser map(AppUserEntity appUserEntity);

    AppUserEntity toEntity(AppUser appUser);

    AppUser map(RegistrationRequestDto dto);
}

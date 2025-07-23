package com.example.library.mapper;

import com.example.library.entity.RoleEntity;
import com.example.library.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role map(RoleEntity roleEntity);

    RoleEntity toEntity(Role role);
}

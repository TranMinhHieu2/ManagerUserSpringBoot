package com.example.HLTSpringboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.HLTSpringboot.dto.request.RoleRequest;
import com.example.HLTSpringboot.dto.response.RoleResponse;
import com.example.HLTSpringboot.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMappper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}

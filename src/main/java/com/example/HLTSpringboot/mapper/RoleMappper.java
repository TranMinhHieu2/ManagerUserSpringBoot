package com.example.HLTSpringboot.mapper;

import com.example.HLTSpringboot.dto.request.PermissionRequest;
import com.example.HLTSpringboot.dto.request.RoleRequest;
import com.example.HLTSpringboot.dto.response.PermissionResponse;
import com.example.HLTSpringboot.dto.response.RoleResponse;
import com.example.HLTSpringboot.entity.Permission;
import com.example.HLTSpringboot.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMappper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);

}

package com.example.HLTSpringboot.mapper;

import org.mapstruct.Mapper;

import com.example.HLTSpringboot.dto.request.PermissionRequest;
import com.example.HLTSpringboot.dto.response.PermissionResponse;
import com.example.HLTSpringboot.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMappper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}

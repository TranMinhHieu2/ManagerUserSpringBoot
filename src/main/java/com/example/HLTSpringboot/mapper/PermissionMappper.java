package com.example.HLTSpringboot.mapper;

import com.example.HLTSpringboot.dto.request.PermissionRequest;
import com.example.HLTSpringboot.dto.response.PermissionResponse;
import com.example.HLTSpringboot.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMappper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);

}

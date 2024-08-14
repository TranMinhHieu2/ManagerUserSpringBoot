package com.example.HLTSpringboot.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.HLTSpringboot.dto.request.RoleRequest;
import com.example.HLTSpringboot.dto.response.RoleResponse;
import com.example.HLTSpringboot.mapper.RoleMappper;
import com.example.HLTSpringboot.repository.PermissionRepository;
import com.example.HLTSpringboot.repository.RoleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleSevice {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMappper roleMappper;

    public RoleResponse create(RoleRequest request) {
        var role = roleMappper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMappper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll() {
        var roles = roleRepository.findAll();
        return roles.stream().map(roleMappper::toRoleResponse).toList();
    }

    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}

package com.example.HLTSpringboot.service;

import com.example.HLTSpringboot.dto.request.PermissionRequest;
import com.example.HLTSpringboot.dto.response.PermissionResponse;
import com.example.HLTSpringboot.entity.Permission;
import com.example.HLTSpringboot.mapper.PermissionMappper;
import com.example.HLTSpringboot.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMappper permissionMappper;

    public PermissionResponse create(PermissionRequest request){
        Permission permission=permissionMappper.toPermission(request);
        permission=permissionRepository.save(permission);
        return permissionMappper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll(){
        var permissions= permissionRepository.findAll();
        return permissions.stream().map(permissionMappper :: toPermissionResponse).toList();
    }

    public void deletePermission(String permission){
        permissionRepository.deleteById(permission);
    }
}

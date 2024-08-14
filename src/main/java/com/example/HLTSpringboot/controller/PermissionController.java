package com.example.HLTSpringboot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.HLTSpringboot.dto.request.ApiResponse;
import com.example.HLTSpringboot.dto.request.PermissionRequest;
import com.example.HLTSpringboot.dto.response.PermissionResponse;
import com.example.HLTSpringboot.service.PermissionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> delte(@PathVariable String permission) {
        permissionService.deletePermission(permission);
        return ApiResponse.<Void>builder().build();
    }
}

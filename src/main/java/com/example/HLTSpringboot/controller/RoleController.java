package com.example.HLTSpringboot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.HLTSpringboot.dto.request.ApiResponse;
import com.example.HLTSpringboot.dto.request.RoleRequest;
import com.example.HLTSpringboot.dto.response.RoleResponse;
import com.example.HLTSpringboot.service.RoleSevice;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    RoleSevice roleSevice;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleSevice.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleSevice.getAll())
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> delte(@PathVariable String role) {
        roleSevice.delete(role);
        return ApiResponse.<Void>builder().build();
    }
}

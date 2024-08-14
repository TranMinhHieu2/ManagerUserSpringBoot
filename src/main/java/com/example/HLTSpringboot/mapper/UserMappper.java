package com.example.HLTSpringboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.HLTSpringboot.dto.request.UserCreationRequest;
import com.example.HLTSpringboot.dto.request.UserUpdateRequest;
import com.example.HLTSpringboot.dto.response.UserResponse;
import com.example.HLTSpringboot.entity.User;

@Mapper(componentModel = "spring")
public interface UserMappper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}

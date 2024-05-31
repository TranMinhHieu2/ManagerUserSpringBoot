package com.example.HLTSpringboot.service;

import com.example.HLTSpringboot.dto.request.UserCreationRequest;
import com.example.HLTSpringboot.dto.request.UserUpdateRequest;
import com.example.HLTSpringboot.dto.response.UserResponse;
import com.example.HLTSpringboot.entity.User;
import com.example.HLTSpringboot.enums.Role;
import com.example.HLTSpringboot.exception.AppException;
import com.example.HLTSpringboot.exception.ErrorCode;
import com.example.HLTSpringboot.mapper.UserMappper;
import com.example.HLTSpringboot.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@RequiredArgsConstructor tự động tạo constructor cho tất cả biến có difine là final => ko cần @Autowrite
//kết hợp với @FieldDefaults với atribute level sẽ ko cần private,
// và atribute makeFinal=true là tất cả các biến thành final
public class UserService {
    UserRepository userRepository;
    UserMappper userMappper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request){
        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMappper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles=new HashSet<>();
        roles.add(Role.USER.name());

        user.setRoles(roles);

        return userMappper.toUserResponse(userRepository.save(user));
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public UserResponse getUser(String id){
        return userMappper.toUserResponse(userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found")));
    }

    public UserResponse updateUser(String id, UserUpdateRequest request){
        User user= userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
        userMappper.updateUser(user,request);

        return userMappper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
}

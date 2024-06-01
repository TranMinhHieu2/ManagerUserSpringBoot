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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
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

//    chỉ những ai có role admin mới có thể gọi getUsers sử dụng @PreAuthorize
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers(){
        log.info("In method get users");
        return userRepository.findAll().stream()
                .map(userMappper::toUserResponse).toList();
    }

//user chi co the lay dc thong tin cua mk
    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(String id){
        return userMappper.toUserResponse(userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found")));
    }

    public UserResponse getMyInfo(){
//        Lay thong tin cua user dang dang nhap
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(
                ()-> new AppException(ErrorCode.USER_EXISTED));

        return userMappper.toUserResponse(user);
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

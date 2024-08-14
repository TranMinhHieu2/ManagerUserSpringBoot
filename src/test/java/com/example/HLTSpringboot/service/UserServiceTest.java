package com.example.HLTSpringboot.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import com.example.HLTSpringboot.dto.request.UserCreationRequest;
import com.example.HLTSpringboot.dto.response.UserResponse;
import com.example.HLTSpringboot.entity.User;
import com.example.HLTSpringboot.exception.AppException;
import com.example.HLTSpringboot.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserCreationRequest request;
    private UserResponse userResponse;
    private LocalDate dob;
    private User user;

    @Autowired
    void initData() {

        dob = LocalDate.of(1990, 1, 1);

        request = UserCreationRequest.builder()
                .username("Rayen")
                .firstName("rayen")
                .lastName("iso")
                .password("12345678")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("hasdfjaslaaa")
                .username("Rayen")
                .firstName("rayen")
                .lastName("iso")
                .dob(dob)
                .build();

        user = User.builder()
                .id("hasdfjaslaaa")
                .username("Rayen")
                .firstName("rayen")
                .lastName("iso")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() {
        //        GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        //        WHEN
        var response = userService.createUser(request);

        //        THEN
        Assertions.assertThat(response.getId()).isEqualTo("hasdfjaslaaa");
        Assertions.assertThat(response.getUsername()).isEqualTo("Rayen");
    }

    @Test
    void createUser_userExisted_fail() {
        //        GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        //        WHEN
        var exception = assertThrows(AppException.class, () -> userService.createUser(request));

        //        THEN
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1002);
    }

    @Test
    @WithMockUser(username = "Rayen")
    void getMyInfo_validRequest_success() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        var response = userService.getMyInfo();

        Assertions.assertThat(response.getId()).isEqualTo("hasdfjaslaaa");
        Assertions.assertThat(response.getUsername()).isEqualTo("Rayen");
    }

    @Test
    @WithMockUser(username = "Rayen")
    void getMyInfo_userNotFound_error() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.ofNullable(null));

        var exception = assertThrows(AppException.class, () -> userService.getMyInfo());

        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1005);
    }
}

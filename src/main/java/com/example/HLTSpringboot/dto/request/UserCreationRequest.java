package com.example.HLTSpringboot.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;

import com.example.HLTSpringboot.validator.DobContraint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
    String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;

    String firstName;
    String lastName;

    @DobContraint(min = 18, message = "INVALID_DOB")
    LocalDate dob;
}

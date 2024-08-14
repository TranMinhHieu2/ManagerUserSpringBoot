package com.example.HLTSpringboot.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
// mất những trường null tăng performen
public class ApiResponse<T> {
    @Builder.Default
    private int code = 1000;

    private String message;
    private T result;
}

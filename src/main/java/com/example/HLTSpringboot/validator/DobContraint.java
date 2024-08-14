package com.example.HLTSpringboot.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({FIELD}) /* dùng cho loại j VD: biến FIELD */
@Retention(RUNTIME) /* xử lý lúc nào */
@Constraint(validatedBy = {DobValidator.class})
public @interface DobContraint {
    String message() default "Invalid date of birth";

    int min();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

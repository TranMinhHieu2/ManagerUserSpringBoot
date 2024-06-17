package com.example.HLTSpringboot.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

//hai dau vao laf annotation voi kieu dlieu cua field dung annotation
public class DobValidator implements ConstraintValidator<DobContraint, LocalDate> {

    private int min;

    //    get thong so annotation
    @Override
    public void initialize(DobContraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }

//    kiem tra xem data co dung hay ko
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.isNull(localDate))
            return true;
//        tính số tuổi
        long years = ChronoUnit.YEARS.between(localDate, LocalDate.now());

        return years >= min;
    }
}

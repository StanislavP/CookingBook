package com.example.CookingBook.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.example.CookingBook.models.DTO.UserRegisterDTO;

public class PasswordMatcher implements ConstraintValidator<PasswordMatch, UserRegisterDTO> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterDTO userRegisterDTO, ConstraintValidatorContext context) {

        if (userRegisterDTO.getPassword() != null &&
        userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) return true;

        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode("confirmPassword")
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}

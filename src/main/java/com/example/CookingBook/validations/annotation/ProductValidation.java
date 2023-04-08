package com.example.CookingBook.validations.annotation;

import com.example.CookingBook.validations.userValidation.ProductValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = ProductValidator.class)
public @interface ProductValidation {
    String message() default "Product not found!!!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

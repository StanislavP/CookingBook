package com.example.CookingBook.validations.userValidation;

import com.example.CookingBook.models.DTO.IngredientDTO;
import com.example.CookingBook.services.ProductService;
import com.example.CookingBook.validations.annotation.ProductValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductValidator implements ConstraintValidator<ProductValidation, IngredientDTO> {

    private final ProductService productService;

    public ProductValidator(ProductService productService) {

        this.productService = productService;
    }

    @Override
    public void initialize(ProductValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(IngredientDTO value, ConstraintValidatorContext context) {



        if (productService.findByNameOptional(value.getProductName()).isPresent()) return true;

        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode("productName")
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}

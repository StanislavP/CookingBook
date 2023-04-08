package com.example.CookingBook.models.DTO;

import com.example.CookingBook.enums.MeasureUnits;
import com.example.CookingBook.validations.annotation.ProductValidation;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
@ProductValidation
public class IngredientDTO {
    private Long id;

    @NotNull
    @Size(min = 1, max = 20)
    private String productName;

//    @Positive(message = "Quantity should be greater than 0.")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=4, fraction=3)
    private BigDecimal quantity;

    @NotNull
    private MeasureUnits measureUnit;

    public IngredientDTO() {
    }

    public Long getId() {
        return id;
    }

    public IngredientDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public IngredientDTO setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public IngredientDTO setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
        return this;
    }

    public MeasureUnits getMeasureUnit() {
        return measureUnit;
    }

    public IngredientDTO setMeasureUnit(MeasureUnits measureUnit) {
        this.measureUnit = measureUnit;
        return this;
    }
}

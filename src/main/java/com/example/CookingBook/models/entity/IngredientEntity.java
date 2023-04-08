package com.example.CookingBook.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "ingredients")
public class IngredientEntity extends BaseEntity {

    @ManyToOne
    private ProductEntity product;

    @Column
    private BigDecimal quantity;

    @ManyToOne
    private MeasureUnitEntity measureUnit;

    public IngredientEntity() {
    }

    public ProductEntity getProduct() {
        return product;
    }

    public IngredientEntity setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public IngredientEntity setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
        return this;
    }

    public MeasureUnitEntity getMeasureUnit() {
        return measureUnit;
    }

    public IngredientEntity setMeasureUnit(MeasureUnitEntity measureUnit) {
        this.measureUnit = measureUnit;
        return this;
    }
}

package com.example.CookingBook.models.DTO;

import com.example.CookingBook.enums.Categories;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductViewDTO {

    private Long id;

    @NotEmpty(message = "Product name is required.")
    @Size(min = 2, max = 20, message = "Product name size 2 to 20.")
    private String productName;

    @NotNull(message = "Category is required.")
    private Categories category;

    private String description;

    public Long getId() {
        return id;
    }

    public ProductViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public ProductViewDTO() {
    }

    public String getProductName() {
        return productName;
    }

    public ProductViewDTO setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Categories getCategory() {
        return category;
    }

    public ProductViewDTO setCategory(Categories category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductViewDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}

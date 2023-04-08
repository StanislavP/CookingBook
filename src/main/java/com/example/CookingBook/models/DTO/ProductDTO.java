package com.example.CookingBook.models.DTO;

import com.example.CookingBook.enums.Categories;
import com.example.CookingBook.models.entity.CategoryEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Locale;

public class ProductDTO {

    @NotEmpty(message = "Product name is required.")
    @Size(min = 2,max = 20, message = "Product name size 2 to 20.")
    private String productName;

    @NotNull(message = "Category is required.")
    private Categories category;

    private String description;

    public ProductDTO() {
    }

    public String getProductName() {
        return productName;
    }

    public ProductDTO setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Categories getCategory() {
        return category;
    }

    public ProductDTO setCategory(Categories category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}

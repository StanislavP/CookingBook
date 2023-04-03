package com.example.CookingBook.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    @Column
    private String name;

    @ManyToOne
    private CategoryEntity category;

    @Column
    private String description;

    public ProductEntity() {
    }

    public String getName() {
        return name;
    }

    public ProductEntity setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public ProductEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}

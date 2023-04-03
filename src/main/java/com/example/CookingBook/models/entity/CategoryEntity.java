package com.example.CookingBook.models.entity;

import com.example.CookingBook.enums.Categories;
import jakarta.persistence.*;

@Entity()
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

    @Enumerated(value = EnumType.STRING)
    private Categories categoryType;

    public CategoryEntity() {
    }

    public Categories getCategoryType() {
        return categoryType;
    }

    public CategoryEntity setCategoryType(Categories categoryType) {
        this.categoryType = categoryType;
        return this;
    }

}

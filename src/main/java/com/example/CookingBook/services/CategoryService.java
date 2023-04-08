package com.example.CookingBook.services;

import com.example.CookingBook.enums.Categories;
import com.example.CookingBook.models.entity.CategoryEntity;
import com.example.CookingBook.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService( CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity findCategoryByName(Categories categorie)
    {
        return categoryRepository.findByCategoryType(categorie)
                .orElseThrow(() -> new CategoryNotFoundException("Category with name " + categorie.name() + " not found!"));
    }
}

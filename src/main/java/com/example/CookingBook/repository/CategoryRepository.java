package com.example.CookingBook.repository;

import com.example.CookingBook.enums.Categories;
import com.example.CookingBook.models.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByCategoryType(Categories categoryType);
}

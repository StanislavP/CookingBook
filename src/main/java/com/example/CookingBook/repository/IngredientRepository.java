package com.example.CookingBook.repository;

import com.example.CookingBook.models.entity.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity,Long> {

}

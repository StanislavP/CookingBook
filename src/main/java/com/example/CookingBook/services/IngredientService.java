package com.example.CookingBook.services;

import com.example.CookingBook.models.DTO.IngredientDTO;
import com.example.CookingBook.models.entity.IngredientEntity;
import com.example.CookingBook.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    public void save(IngredientEntity ingredientEntity){
        ingredientRepository.save(ingredientEntity);
    }
    public void saveAndFlush(IngredientEntity ingredientEntity){
        ingredientRepository.saveAndFlush(ingredientEntity);
    }

    public void removeAll(List<IngredientEntity> items){
        ingredientRepository.deleteAll(items);
    }


}

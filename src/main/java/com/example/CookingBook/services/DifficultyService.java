package com.example.CookingBook.services;

import com.example.CookingBook.enums.Difficulty;
import com.example.CookingBook.models.entity.DifficultyEntity;
import com.example.CookingBook.repository.DifficultyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DifficultyService {

    private final DifficultyRepository difficultyRepository;

    public DifficultyService(DifficultyRepository difficultyRepository) {
        this.difficultyRepository = difficultyRepository;
    }

    public DifficultyEntity findByDifficulty(Difficulty difficulty){
        return difficultyRepository.findByDifficulty(difficulty)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

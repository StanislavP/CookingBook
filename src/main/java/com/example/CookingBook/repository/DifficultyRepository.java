package com.example.CookingBook.repository;

import com.example.CookingBook.enums.Difficulty;
import com.example.CookingBook.models.entity.DifficultyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DifficultyRepository extends JpaRepository<DifficultyEntity,Long> {

    Optional<DifficultyEntity> findByDifficulty(Difficulty difficulty);
}

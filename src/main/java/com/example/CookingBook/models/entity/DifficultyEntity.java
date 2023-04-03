package com.example.CookingBook.models.entity;

import com.example.CookingBook.enums.Difficulty;
import jakarta.persistence.*;

@Entity
@Table(name = "difficulties")
public class DifficultyEntity extends BaseEntity{

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @Column
    private String description;

    public DifficultyEntity() {
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public DifficultyEntity setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DifficultyEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
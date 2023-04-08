package com.example.CookingBook.enums;

public enum Difficulty {
    EASY("Requires little to basic cooking skills and common ingredients."),
    MODERATE("Requires more experience, more prep and cooking time, and maybe some ingredients you don’t already have in your kitchen."),
    HARD("Challenging recipes that require more advanced skills and experience and maybe some special equipment.");

    public final String description;


    Difficulty(String description) {
        this.description = description;
    }
}

package com.example.CookingBook.enums;

public enum MeasureUnits {
    DESSERTSPOON("dsp."),
    TEASPOON("tsp."),
    TABLESPOON("tbsp."),
    COFFEESPOON("csp."),
    LITER("l"),
    MILLILITER("ml"),
    MILLIGRAM("mg"),
    GRAM("g"),
    KILOGRAM("kg"),
    CUP("c");
    public final String description;
    MeasureUnits(String description) {
        this.description = description;
    }
}

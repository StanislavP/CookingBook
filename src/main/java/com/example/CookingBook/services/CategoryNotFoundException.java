package com.example.CookingBook.services;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String s) {
        super(s);
    }
}

package com.example.CookingBook.utils;

public class RandomNumber {
    public static String generateMyNumber()
    {
        int aNumber = 0;
        aNumber = (int)((Math.random() * 9000000)+1000000);
        return "%d".formatted(aNumber);
    }

}
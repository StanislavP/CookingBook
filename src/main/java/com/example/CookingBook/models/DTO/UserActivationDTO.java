package com.example.CookingBook.models.DTO;

public class UserActivationDTO {

    String token;


    public UserActivationDTO() {
    }

    public String getToken() {
        return token;
    }

    public UserActivationDTO setToken(String token) {
        this.token = token;
        return this;
    }
}

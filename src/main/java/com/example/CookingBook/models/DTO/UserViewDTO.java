package com.example.CookingBook.models.DTO;

public class UserViewDTO {
    private String email;
    private String firstName;
    private String lastName;

    public UserViewDTO() {
    }

    public String getEmail() {
        return email;
    }

    public UserViewDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserViewDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserViewDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}

package com.example.CookingBook.models.DTO;

import com.example.CookingBook.enums.UserRoles;

public class UserChangeRoleDTO {


    private String email;

    private UserRoles userRole;

    public UserChangeRoleDTO() {
    }

    public String getEmail() {
        return email;
    }

    public UserChangeRoleDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserRoles getUserRole() {
        return userRole;
    }

    public UserChangeRoleDTO setUserRole(UserRoles userRole) {
        this.userRole = userRole;
        return this;
    }
}

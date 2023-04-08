package com.example.CookingBook.models.entity;

import com.example.CookingBook.enums.UserRoles;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity extends  BaseEntity {

    @Enumerated(value = EnumType.STRING)
    private UserRoles userRole;

    public RoleEntity() {
    }

    public UserRoles getUserRole() {
        return userRole;
    }

    public RoleEntity setUserRole(UserRoles userRole) {
        this.userRole = userRole;
        return this;
    }
}

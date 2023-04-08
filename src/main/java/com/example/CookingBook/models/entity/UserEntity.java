package com.example.CookingBook.models.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends  BaseEntity {

    @Column
    private String firstName; //–  first name of the user.

    @Column
    private String lastName; //–  last name of the user.


    @Column(name = "password")
    private String password;

    @Column(unique = true,nullable = false)
    private String email;

    @Column
    private Boolean isActive; //– true OR false.

    @ManyToMany(fetch = FetchType.EAGER)
    private List<RoleEntity> roles;

    public UserEntity() {
    }

    public Boolean getActive() {
        return isActive;
    }

    public UserEntity setActive(Boolean active) {
        isActive = active;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }
}

package com.example.CookingBook.models.DTO;

import com.example.CookingBook.enums.UserRoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Map;

public class UserDTO extends User {

    private String email;
    private String firstName;
    private String lastName;

    private Map<UserRoles, Boolean> roles;

    public UserDTO (String username, String password, Collection<? extends GrantedAuthority> authorities) {

        super(username, password, authorities);
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public boolean isAdmin() {

        return getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().contains(UserRoles.ADMIN.name()));
    }

    public boolean isModerator() {

        return getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().contains(UserRoles.MODERATOR.name()));
    }

}

package com.example.CookingBook.services;

import com.example.CookingBook.models.entity.RoleEntity;
import com.example.CookingBook.models.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.CookingBook.repository.UserRepository;

import java.util.List;


public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!"));
    }


    private UserDetails map(UserEntity userEntity) {
        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                extractAuthorities(userEntity));
    }


    private List<GrantedAuthority> extractAuthorities(UserEntity userEntity) {
        return userEntity
                .getRoles()
                .stream()
                .map(this::mapRole)
                .toList();
    }

    private GrantedAuthority mapRole(RoleEntity roleEntity) {
        return new SimpleGrantedAuthority("ROLE_" + roleEntity.getUserRole().name());
    }
}

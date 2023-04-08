package com.example.CookingBook.services;

import com.example.CookingBook.models.entity.RoleEntity;
import com.example.CookingBook.models.entity.UserEntity;
import com.example.CookingBook.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails tempUser = userRepository.findByEmail(email)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + email + " not found!"));
        if (!tempUser.isEnabled()) {
            throw new UsernameNotFoundException("User with name " + email + " not activated!");
        }
        return tempUser;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // TODO Auto-generated method stub
//        User user=userservice.getUserByusername(username);
//
//        if(user != null && user.isEnabled()) {//here you can check that
//            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
//            return buildUserForAuthentication(user, authorities);
//        }
//
//        else {
//            throw new UsernameNotFoundException("username not found");
//        }
//
//    }


    private UserDetails map(UserEntity userEntity) {
        return new User(userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getActive(),
                true,
                true,
                true,
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

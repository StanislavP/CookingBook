package com.example.CookingBook.services;

import com.example.CookingBook.enums.UserRoles;
import com.example.CookingBook.models.entity.RoleEntity;
import com.example.CookingBook.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import com.example.CookingBook.models.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.CookingBook.repository.UserRepository;

@Service
public class InitService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void initBase() {
        initRoles();
        initUsers();
    }

    private void initRoles() {
        if (roleRepository.count() == 0) {
            var adminRole = new RoleEntity().setUserRole(UserRoles.ADMIN);
            var moderatorRole = new RoleEntity().setUserRole(UserRoles.MODERATOR);
            roleRepository.save(adminRole);
            roleRepository.save(moderatorRole);
        }
    }

    private void initUsers() {
        var user1 = new UserEntity()
                .setUsername("goshko")
                .setPassword(passwordEncoder.encode("123456"))
                .setEmail("goshko@abv.hu")
                .setRoles(roleRepository.findAll());

        userRepository.save(user1);
    }


}

package com.example.CookingBook.services;

import com.example.CookingBook.enums.Categories;
import com.example.CookingBook.enums.Difficulty;
import com.example.CookingBook.enums.MeasureUnits;
import com.example.CookingBook.enums.UserRoles;
import com.example.CookingBook.models.entity.*;
import com.example.CookingBook.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InitService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;
    private final DifficultyRepository difficultyRepository;
    private final MeasureUnitRepository measureUnitRepository;

    @Autowired
    public InitService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, CategoryRepository categoryRepository, DifficultyRepository difficultyRepository, MeasureUnitRepository measureUnitRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.categoryRepository = categoryRepository;
        this.difficultyRepository = difficultyRepository;
        this.measureUnitRepository = measureUnitRepository;
    }

    @PostConstruct
    private void initBase() {
        initRoles();
        initUsers();
        initCategories();
        initDifficulties();
        initMeasureUnits();
    }

    private void initRoles() {
        if (roleRepository.count() == 0) {
            var adminRole = new RoleEntity().setUserRole(UserRoles.ADMIN);
            var moderatorRole = new RoleEntity().setUserRole(UserRoles.MODERATOR);
            roleRepository.save(adminRole);
            roleRepository.save(moderatorRole);
        }
    }
    private void initCategories() {
        if (categoryRepository.count() == 0) {
            for (Categories category: Categories.values()){
                CategoryEntity categoryEntity = new CategoryEntity();
                categoryEntity.setCategoryType(category);
                categoryRepository.save(categoryEntity);
            }


        }
    }

    private void initDifficulties() {
        if (difficultyRepository.count() == 0) {
            for (Difficulty difficulty: Difficulty.values()){
                DifficultyEntity difficultyEntity = new DifficultyEntity();
                difficultyEntity.setDifficulty(difficulty);
                difficultyEntity.setDescription(difficulty.description);
                difficultyRepository.save(difficultyEntity);
            }
        }
    }

    private void initMeasureUnits() {
        if (measureUnitRepository.count() == 0) {
            for (MeasureUnits measureUnits: MeasureUnits.values()){
                MeasureUnitEntity measureUnitEntity = new MeasureUnitEntity();
                measureUnitEntity.setUnitType(measureUnits);
                measureUnitEntity.setUnitName(measureUnits.description);
                measureUnitRepository.save(measureUnitEntity);
            }
        }
    }

    private void initUsers() {
        if (this.userRepository.count() == 0) {
            var user1 = new UserEntity()
                    .setFirstName("Goshko")
                    .setLastName("Goshev")
                    .setPassword(passwordEncoder.encode("123456"))
                    .setEmail("goshko@abv.bg")
                    .setActive(true)
                    .setRoles(roleRepository.findAll());

            userRepository.save(user1);
        }
    }


}

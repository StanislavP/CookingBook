package com.example.CookingBook.repository;

import com.example.CookingBook.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String userName);
    UserEntity findByEmailIgnoreCase(String emailId);
}

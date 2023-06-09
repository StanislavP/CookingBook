package com.example.CookingBook.repository;

import com.example.CookingBook.enums.UserRoles;
import com.example.CookingBook.models.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {


    Optional<RoleEntity> findByUserRole(UserRoles userRoles);
}

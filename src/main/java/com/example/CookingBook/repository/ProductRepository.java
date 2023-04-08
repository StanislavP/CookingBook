package com.example.CookingBook.repository;

import com.example.CookingBook.models.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByName(String name);

    @Override
    Optional<ProductEntity> findById(Long aLong);

   boolean existsByName(String name);
}

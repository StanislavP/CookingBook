package com.example.CookingBook.repository;

import com.example.CookingBook.models.entity.ReceiptEntity;
import com.example.CookingBook.models.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Repository
public interface ReceiptRepository extends JpaRepository<ReceiptEntity,Long> {


    Optional<ReceiptEntity> findRecipeByName (String title);

    Set<ReceiptEntity> findByUser_Id(Long id);

    List<ReceiptEntity> findAllByUser (UserEntity user);

    Page<ReceiptEntity> findAllByUser(UserEntity user,Pageable pageable);

}

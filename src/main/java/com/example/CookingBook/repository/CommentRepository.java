package com.example.CookingBook.repository;

import com.example.CookingBook.models.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {


}

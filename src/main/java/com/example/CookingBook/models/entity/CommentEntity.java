package com.example.CookingBook.models.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity{

    @Column
    private String comment;

    @ManyToOne
    private UserEntity userEntity;

    @OneToOne
    private ReceiptEntity receiptEntity;

}

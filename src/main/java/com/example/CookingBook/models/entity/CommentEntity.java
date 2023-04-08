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

    public CommentEntity() {
    }

    public String getComment() {
        return comment;
    }

    public CommentEntity setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public CommentEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
}

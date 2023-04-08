package com.example.CookingBook.models.DTO;

import com.example.CookingBook.models.entity.UserEntity;

public class CommentDTO {

    private String comment;

    public CommentDTO() {
    }

    public String getComment() {
        return comment;
    }

    public CommentDTO setComment(String comment) {
        this.comment = comment;
        return this;
    }

}

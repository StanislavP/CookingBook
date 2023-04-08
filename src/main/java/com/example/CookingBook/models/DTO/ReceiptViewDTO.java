package com.example.CookingBook.models.DTO;

import com.example.CookingBook.models.entity.CommentEntity;
import com.example.CookingBook.models.entity.DifficultyEntity;
import com.example.CookingBook.models.entity.IngredientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.Date;
import java.util.List;

public class ReceiptViewDTO {

    private Long id;

    private String name;

    private int cooking_time;

    private int prep_time;

    private int servings;

    private String description;

    private String preparation;

    private String picture_url;

    private List<IngredientEntity> ingredients;

    private DifficultyEntity difficulty;

    private Date createdDate;

    private List<CommentEntity> comments;


    public int getCommentsSize(){
        return comments.size();
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public ReceiptViewDTO setComments(List<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ReceiptViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public ReceiptViewDTO setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public ReceiptViewDTO() {
    }

    public String getName() {
        return name;
    }

    public ReceiptViewDTO setName(String name) {
        this.name = name;
        return this;
    }

    public int getCooking_time() {
        return cooking_time;
    }

    public ReceiptViewDTO setCooking_time(int cooking_time) {
        this.cooking_time = cooking_time;
        return this;
    }

    public int getPrep_time() {
        return prep_time;
    }

    public ReceiptViewDTO setPrep_time(int prep_time) {
        this.prep_time = prep_time;
        return this;
    }

    public int getServings() {
        return servings;
    }

    public ReceiptViewDTO setServings(int servings) {
        this.servings = servings;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ReceiptViewDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPreparation() {
        return preparation;
    }

    public ReceiptViewDTO setPreparation(String preparation) {
        this.preparation = preparation;
        return this;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public ReceiptViewDTO setPicture_url(String picture_url) {
        this.picture_url = picture_url;
        return this;
    }

    public List<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public ReceiptViewDTO setIngredients(List<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public DifficultyEntity getDifficulty() {
        return difficulty;
    }

    public ReceiptViewDTO setDifficulty(DifficultyEntity difficulty) {
        this.difficulty = difficulty;
        return this;
    }
}

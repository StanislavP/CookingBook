package com.example.CookingBook.models.entity;

import com.example.CookingBook.repository.UserRepository;
import jakarta.persistence.*;
import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "receipts")
public class ReceiptEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private int cooking_time;

    @Column
    private int prep_time;

    @Column
    private int servings;

    @Column
    private String description;

    @Column
    private String preparation;

    @Column
    private String picture_url;

    @OneToMany
    private List<IngredientEntity> ingredients;

    @ManyToOne
    private DifficultyEntity difficulty;

    @ManyToOne
    private UserEntity user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToMany
    private List<CommentEntity> comments;

    public ReceiptEntity() {
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public ReceiptEntity setComments(List<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public ReceiptEntity setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public ReceiptEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getCooking_time() {
        return cooking_time;
    }

    public ReceiptEntity setCooking_time(int cooking_time) {
        this.cooking_time = cooking_time;
        return this;
    }

    public int getPrep_time() {
        return prep_time;
    }

    public ReceiptEntity setPrep_time(int prep_time) {
        this.prep_time = prep_time;
        return this;
    }

    public int getServings() {
        return servings;
    }

    public ReceiptEntity setServings(int servings) {
        this.servings = servings;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ReceiptEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPreparation() {
        return preparation;
    }

    public ReceiptEntity setPreparation(String preparation) {
        this.preparation = preparation;
        return this;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public ReceiptEntity setPicture_url(String picture_url) {
        this.picture_url = picture_url;
        return this;
    }

    public List<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public ReceiptEntity setIngredients(List<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public DifficultyEntity getDifficulty() {
        return difficulty;
    }

    public ReceiptEntity setDifficulty(DifficultyEntity difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public ReceiptEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}

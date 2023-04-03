package com.example.CookingBook.models.entity;

import com.example.CookingBook.repository.UserRepository;
import jakarta.persistence.*;
import org.w3c.dom.Text;

import java.util.List;

@Entity
@Table(name = "receipts")
public class ReceiptEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private int cooking_time;

    @Column
    private String description;

    @Column
    private String preparation;

    @ManyToMany
    private List<IngredientEntity> ingredients;

    @ManyToOne
    private DifficultyEntity difficulty;

    @ManyToOne
    private UserEntity user;
}

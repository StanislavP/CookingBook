package com.example.CookingBook.models.DTO;

import com.example.CookingBook.enums.Difficulty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ReceiptAddDTO {

    @NotEmpty(message = "Recipe title is required.")
    @Size(min = 1,max = 50)
    private String name;

    private List<@Valid IngredientDTO> ingredientList;

    @Positive(message = "Cooking time should be greater than 0.")
    private int cooking_time;

    @Positive(message = "Preparation should be greater than 0.")
    private int prep_time;

    @Positive(message = "Servings should be greater than 0.")
    private int servings;

    @NotEmpty(message = "Description is required.")
    @Size( max = 512)
    private String description;

    @NotEmpty(message = "Cooking procedure is required.")
    @Size( max = 4096)
    private String preparation;

    @NotNull(message = "Difficulty status is required.")
    private Difficulty difficulty;

    private long user_id;

    private MultipartFile photo;

    public ReceiptAddDTO() {
    }

    public String getName() {
        return name;
    }

    public ReceiptAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<IngredientDTO> getIngredientList() {
        return ingredientList;
    }

    public ReceiptAddDTO setIngredientList(List<IngredientDTO> ingredientList) {
        this.ingredientList = ingredientList;
        return this;
    }

    public int getCooking_time() {
        return cooking_time;
    }

    public ReceiptAddDTO setCooking_time(int cooking_time) {
        this.cooking_time = cooking_time;
        return this;
    }

    public int getPrep_time() {
        return prep_time;
    }

    public ReceiptAddDTO setPrep_time(int prep_time) {
        this.prep_time = prep_time;
        return this;
    }

    public int getServings() {
        return servings;
    }

    public ReceiptAddDTO setServings(int servings) {
        this.servings = servings;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ReceiptAddDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPreparation() {
        return preparation;
    }

    public ReceiptAddDTO setPreparation(String preparation) {
        this.preparation = preparation;
        return this;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public ReceiptAddDTO setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public long getUser_id() {
        return user_id;
    }

    public ReceiptAddDTO setUser_id(long user_id) {
        this.user_id = user_id;
        return this;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public ReceiptAddDTO setPhoto(MultipartFile photo) {
        this.photo = photo;
        return this;
    }
}

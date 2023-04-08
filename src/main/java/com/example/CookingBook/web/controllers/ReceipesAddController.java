package com.example.CookingBook.web.controllers;

import com.example.CookingBook.enums.MeasureUnits;
import com.example.CookingBook.models.DTO.IngredientDTO;
import com.example.CookingBook.models.DTO.ProductViewDTO;
import com.example.CookingBook.models.DTO.ReceiptAddDTO;
import com.example.CookingBook.models.DTO.ReceiptViewDTO;
import com.example.CookingBook.models.entity.*;
import com.example.CookingBook.services.*;
import com.example.CookingBook.web.annotations.PageTitle;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Controller
@RequestMapping("/receipes")
public class ReceipesAddController {
    private static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult.";
    private final MeasureUnitsService measureUnitsService;
    private final UserService userService;
    private ModelMapper modelMapper;
    private CloudinaryService cloudinaryService;
    private final ProductService productService;
    private final IngredientService ingredientService;
    private final DifficultyService difficultyService;
    private final ReceiptService receiptService;

    public ReceipesAddController(MeasureUnitsService measureUnitsService, UserService userService, ModelMapper modelMapper, CloudinaryService cloudinaryService, ProductService productService, IngredientService ingredientService, DifficultyService difficultyService, ReceiptService receiptService) {
        this.measureUnitsService = measureUnitsService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
        this.productService = productService;
        this.ingredientService = ingredientService;
        this.difficultyService = difficultyService;
        this.receiptService = receiptService;
    }

    @GetMapping("/edit/{id}")
    @PageTitle("Receipt edit")
    public String getEditReceipt(Model model, @PathVariable long id) {
        ReceiptEntity receiptEntity = receiptService.findById(id);
        ReceiptAddDTO receiptAddDTO = modelMapper.map(receiptEntity, ReceiptAddDTO.class);
        List<IngredientDTO> ingredientDTOS = new ArrayList<>();
        for (IngredientEntity ingredientEntity : receiptEntity.getIngredients()) {
            IngredientDTO ingredientDTO = new IngredientDTO();
            ingredientDTO.setQuantity(ingredientEntity.getQuantity())
                    .setProductName(ingredientEntity.getProduct().getName())
                    .setMeasureUnit(ingredientEntity.getMeasureUnit().getUnitType());
            ingredientDTOS.add(ingredientDTO);
        }
        receiptAddDTO.setIngredientList(ingredientDTOS);

        model.addAttribute("receiptAddDTO", receiptAddDTO);
        model.addAttribute("id", id);

        return "receipes/receiptEdit";
    }

    @PostMapping("/edit/{id}")
    public String editReceipt(@PathVariable long id,
                              @Valid @ModelAttribute ReceiptAddDTO receiptAddDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Principal principal) {

        if (bindingResult.hasErrors()) {

            redirectAttributes
                    .addFlashAttribute("receiptAddDTO", receiptAddDTO)
                    .addFlashAttribute(BINDING_RESULT_PATH + "receiptAddDTO", bindingResult);

            return "redirect:/receipes/edit/" + id;
        }
        UserEntity user = userService.getUserByEmail(principal.getName());
        ReceiptEntity receiptEntity = receiptService.findById(id);
        List<IngredientDTO> ingredients = receiptAddDTO.getIngredientList();
        List<IngredientEntity> ingredientsForReceipt = receiptEntity.getIngredients();
        DifficultyEntity difficulty = difficultyService.findByDifficulty(receiptAddDTO.getDifficulty());
        receiptEntity.getIngredients().clear();
        receiptService.saveAndFlush(receiptEntity);
        ingredientService.removeAll(ingredientsForReceipt);
        for (IngredientDTO ingredient : ingredients) {
            ProductEntity product = productService.findByName(ingredient.getProductName());
            IngredientEntity ingredientEntity = new IngredientEntity();
            MeasureUnitEntity measureUnit = measureUnitsService.getMeasuringUnit(ingredient.getMeasureUnit());
            ingredientEntity
                    .setProduct(product)
                    .setQuantity(ingredient.getQuantity())
                    .setMeasureUnit(measureUnit);
            ingredientsForReceipt.add(ingredientEntity);
            ingredientService.save(ingredientEntity);
        }

        receiptEntity
                .setIngredients(ingredientsForReceipt)
                .setUser(user)
                .setName(receiptAddDTO.getName())
                .setCooking_time(receiptAddDTO.getCooking_time())
                .setPrep_time(receiptAddDTO.getPrep_time())
                .setServings(receiptAddDTO.getServings())
                .setDescription(receiptAddDTO.getDescription())
                .setDifficulty(difficulty)
                .setPreparation(receiptAddDTO.getPreparation())
                .setCreatedDate(new Date());

        receiptService.saveAndFlush(receiptEntity);



        return "redirect:/receipes/view/" + id;

    }

    @PostMapping("/delete/{id}")
    public String editReceipt(@PathVariable Long id, Principal principal) {
        ReceiptEntity receipt = receiptService.findById(id);
        UserEntity user = userService.getUserByEmail(principal.getName());
        if (user.getRoles().isEmpty()) {
            if (!Objects.equals(receipt.getUser().getId(), user.getId()))
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        receiptService.deleteReceipt(receipt);


        return "redirect:/receipes/view_all";
    }

    @GetMapping("/add")
    @PageTitle("Add recipe")
    public String getReceipesAdd() {
        return "receipes/receiptAdd";
    }


    @PostMapping("/add")
    public String postReceipesAdd(Model model,
                                  @Valid ReceiptAddDTO receiptAddDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  Principal principal) throws IOException {


        if (bindingResult.hasErrors()) {

            redirectAttributes
                    .addFlashAttribute("receiptAddDTO", receiptAddDTO)
                    .addFlashAttribute(BINDING_RESULT_PATH + "receiptAddDTO", bindingResult);

            return "redirect:/receipes/add";
        }
        UserEntity user = userService.getUserByEmail(principal.getName());
        ReceiptEntity receiptEntity = new ReceiptEntity();
        String photo = "";
        if(!receiptAddDTO.getPhoto().isEmpty())
            photo = this.cloudinaryService.uploadImage(receiptAddDTO.getPhoto());
        List<IngredientDTO> ingredients = receiptAddDTO.getIngredientList();
        List<IngredientEntity> ingredientsForReceipt = new ArrayList<>();
        DifficultyEntity difficulty = difficultyService.findByDifficulty(receiptAddDTO.getDifficulty());
        for (IngredientDTO ingredient : ingredients) {
            ProductEntity product = productService.findByName(ingredient.getProductName());
            IngredientEntity ingredientEntity = new IngredientEntity();
            MeasureUnitEntity measureUnit = measureUnitsService.getMeasuringUnit(ingredient.getMeasureUnit());
            ingredientEntity
                    .setProduct(product)
                    .setQuantity(ingredient.getQuantity())
                    .setMeasureUnit(measureUnit);
            ingredientsForReceipt.add(ingredientEntity);
            ingredientService.save(ingredientEntity);
        }

        receiptEntity
                .setIngredients(ingredientsForReceipt)
                .setUser(user)
                .setName(receiptAddDTO.getName())
                .setPicture_url(photo)
                .setCooking_time(receiptAddDTO.getCooking_time())
                .setPrep_time(receiptAddDTO.getPrep_time())
                .setServings(receiptAddDTO.getServings())
                .setDescription(receiptAddDTO.getDescription())
                .setDifficulty(difficulty)
                .setComments(new ArrayList<>())
                .setPreparation(receiptAddDTO.getPreparation()).setCreatedDate(new Date());

        receiptService.saveAndFlush(receiptEntity);


        return "redirect:/receipes/view_user_recipes";
    }

    @ModelAttribute(name = "receiptAddDTO")
    public ReceiptAddDTO initReceiptAddDTO() {
        List<IngredientDTO> list = new ArrayList<>();
        list.add(new IngredientDTO().setProductName("").setMeasureUnit(MeasureUnits.DESSERTSPOON).setQuantity(BigDecimal.valueOf(0)));
        return new ReceiptAddDTO().setIngredientList(list);
    }

    @ModelAttribute(name = "receiptViewDTO")
    public ReceiptViewDTO receiptViewDTO() {
        return new ReceiptViewDTO();
    }
}

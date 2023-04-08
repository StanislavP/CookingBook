package com.example.CookingBook.web.controllers;

import com.example.CookingBook.models.DTO.ProductDTO;
import com.example.CookingBook.models.DTO.ProductViewDTO;
import com.example.CookingBook.models.entity.CategoryEntity;
import com.example.CookingBook.models.entity.ProductEntity;
import com.example.CookingBook.services.CategoryService;
import com.example.CookingBook.services.MeasureUnitsService;
import com.example.CookingBook.services.ProductService;
import com.example.CookingBook.web.annotations.PageTitle;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/product")
public class ProductController {
    private static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult.";
    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, MeasureUnitsService measureUnitsService1, ModelMapper modelMapper, CategoryService categoryService) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @GetMapping("/add")
    @PageTitle("Product add")
    public String getProductsAdd(Model model, @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username) {
        return "product/add";
    }

    @GetMapping("/view_all/{page}")
    @PageTitle("Product view")
    public String getProductsView(Model model, @PathVariable int page) {

        Page<ProductEntity> productsPageable = productService.getPageableProductPage(page);

        List<ProductViewDTO> products = productsPageable
                .getContent()
                .stream()
                .map(product -> modelMapper.map(product, ProductViewDTO.class)).
                toList();

        model.addAttribute("isEmpty", products.isEmpty());
        model.addAttribute("isNextVisible", productsPageable.getTotalPages() < productsPageable.getPageable().getPageNumber());
        model.addAttribute("isPrevVisible", productsPageable.getPageable().getPageNumber() > 0);
        model.addAttribute("currentPage", productsPageable.getPageable().getPageNumber());
        model.addAttribute("products", products);


        return "product/viewAll";
    }

    @GetMapping("/edit/{id}")
    @PageTitle("Product edit")
    public String getProductsEdit(Model model, @PathVariable long id) {
        ProductEntity productEntity = productService.findById(id);
        ProductViewDTO product = modelMapper.map(productEntity, ProductViewDTO.class);

        model.addAttribute("product", product);
        model.addAttribute("id", id);

        return "product/edit";
    }

    @PostMapping("/edit/{id}")
    public String getProductsEdit( @PathVariable Long id,
                                  @Valid @ModelAttribute ProductViewDTO product,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {

            redirectAttributes
                    .addFlashAttribute("product", product)
                    .addFlashAttribute(BINDING_RESULT_PATH + "product", bindingResult);

            return "redirect:/product/edit/"+id;
        }
        ProductEntity productEntity = productService.findById(product.getId());
        CategoryEntity category = categoryService.findCategoryByName(product.getCategory());
        productEntity.setCategory(category).setName(product.getProductName()).setDescription(product.getDescription());

        productService.saveAndFlush(productEntity);

        return "redirect:/product/view/"+productEntity.getId();
    }
    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable long id) {

        ProductEntity product = productService.findById(id);
        productService.deleteItem(product);

        return "redirect:/product/view_all";
    }

    @GetMapping("/view_all")
    @PageTitle("Product view")
    public String getProductsView() {

        return "redirect:/product/view_all/0";
    }

    @PostMapping("/add")
    public String postProductDTO(@Valid ProductDTO productDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes
                    .addFlashAttribute("productDTO", productDTO)
                    .addFlashAttribute(BINDING_RESULT_PATH + "productDTO", bindingResult);

            return "redirect:/product/add";
        }

        Long receiptIx = productService.saveProduct(productDTO);

        return "redirect:/product/view/%d".formatted(receiptIx);
    }


    @GetMapping("/view/{id}")
    public String getRecipe(@PathVariable Long id,
                            Model model) {
        ProductDTO product = this.modelMapper.map(this.productService.findById(id), ProductDTO.class);
        model.addAttribute("productDTO", product);

        return "product/view";
    }


    @ModelAttribute(name = "productDTO")
    public ProductDTO initProductDTO() {
        return new ProductDTO();
    }

    @ModelAttribute(name = "product")
    public ProductViewDTO product() {
        return new ProductViewDTO();
    }

}

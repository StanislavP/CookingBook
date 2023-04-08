package com.example.CookingBook.web.controllers.rest;

import com.example.CookingBook.services.ProductService;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.StyledEditorKit;

@RestController
public class ProductRestController {
    private final ProductService productService;
    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/v3/product/isAvailable/{name}")
   public Boolean productNameExist(@PathVariable String name)
        {
        return productService.isProductExist(name);
    }

}

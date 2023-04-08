package com.example.CookingBook.services;

import com.example.CookingBook.models.DTO.ProductDTO;
import com.example.CookingBook.models.entity.ProductEntity;
import com.example.CookingBook.models.entity.ReceiptEntity;
import com.example.CookingBook.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }


    public long saveProduct(ProductDTO newProduct) {
        ProductEntity product = this.modelMapper.map(newProduct, ProductEntity.class);
        product.setCategory(categoryService.findCategoryByName(newProduct.getCategory()));
        return this.productRepository.saveAndFlush(product).getId();
    }

    public void saveAndFlush(ProductEntity product) {
        productRepository.saveAndFlush(product);
    }

    public void deleteItem(ProductEntity product) {
        productRepository.delete(product);
    }

    public ProductEntity findById(Long id) {

        return this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found!"));

    }

    public ProductEntity findByName(String name) {

        return this.productRepository.findByName(name)
                .orElseThrow(() -> new ProductNotFoundException("Product with name " + name + " not found!"));

    }

    public Optional<ProductEntity> findByNameOptional(String name) {

        return this.productRepository.findByName(name);

    }

    public Page<ProductEntity> getPageableProductPage(int page) {
        return productRepository.findAll(PageRequest.of(page, 24, Sort.by(Sort.Direction.ASC, "id")));
    }

    public boolean isProductExist(String name) {
        return productRepository.existsByName(name);
    }

}

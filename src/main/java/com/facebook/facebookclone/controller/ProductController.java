package com.facebook.facebookclone.controller;

import com.facebook.facebookclone.mapper.ProductMapper;
import com.facebook.facebookclone.model.dao.Product;
import com.facebook.facebookclone.model.dto.ProductDto;
import com.facebook.facebookclone.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/marketplace")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;


    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public void addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productMapper.productDtoToProduct(productDto));
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productMapper.productToProductDtoWithUser(productService.getProductById(id));
    }

}

package com.facebook.facebookclone.service.impl;

import com.facebook.facebookclone.model.dao.Product;
import com.facebook.facebookclone.model.dao.User;
import com.facebook.facebookclone.repository.ProductRepository;
import com.facebook.facebookclone.service.ProductService;
import com.facebook.facebookclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final UserService userService;

    private final ProductRepository productRepository;



    @Override
    public void addProduct(Product product) {
        User currentUser = userService.getCurrentUser();

        List<Product> products = currentUser.getProducts();

        product.setUser(currentUser);
        products.add(product);

        productRepository.save(product);
    }



    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }



    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product doesn't exist"));
    }
}


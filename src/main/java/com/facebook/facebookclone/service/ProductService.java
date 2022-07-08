package com.facebook.facebookclone.service;

import com.facebook.facebookclone.model.dao.Product;

public interface ProductService {


    void addProduct(Product product);

    void deleteProductById(Long id);

    Product getProductById(Long id);



}

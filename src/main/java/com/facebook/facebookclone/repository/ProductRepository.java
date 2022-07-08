package com.facebook.facebookclone.repository;

import com.facebook.facebookclone.model.dao.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

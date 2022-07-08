package com.facebook.facebookclone.mapper.impl;

import com.facebook.facebookclone.mapper.ProductMapper;
import com.facebook.facebookclone.mapper.UserMapper;
import com.facebook.facebookclone.model.dao.Product;
import com.facebook.facebookclone.model.dto.ProductDto;
import com.facebook.facebookclone.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapperImpl implements ProductMapper {

    private final UserMapper userMapper;

    @Override
    public ProductDto productToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .lastModifiedBy(product.getLastModifiedBy())
                .createdBy(product.getCreatedBy())
                .createdDate(product.getCreatedDate())
                .lastModifiedDate(product.getLastModifiedDate())
                .price(product.getPrice())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .build();
    }

    @Override
    public Product productDtoToProduct(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .createdBy(productDto.getCreatedBy())
                .createdDate(productDto.getCreatedDate())
                .lastModifiedBy(productDto.getLastModifiedBy())
                .lastModifiedDate(productDto.getLastModifiedDate())
                .price(productDto.getPrice())
                .productName(productDto.getProductName())
                .quantity(productDto.getQuantity())
                .build();
    }

    @Override
    public ProductDto productToProductDtoWithUser(Product product) {
        ProductDto productDto = productToProductDto(product);
        productDto.setUserDto(userMapper.userToUserDto(product.getUser()));

        return productDto;
    }
}

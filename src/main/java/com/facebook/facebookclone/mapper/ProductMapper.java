package com.facebook.facebookclone.mapper;

import com.facebook.facebookclone.model.dao.Product;
import com.facebook.facebookclone.model.dto.ProductDto;

public interface ProductMapper {

    ProductDto productToProductDto(Product product);

    Product productDtoToProduct(ProductDto productDto);

    ProductDto productToProductDtoWithUser(Product product);

}

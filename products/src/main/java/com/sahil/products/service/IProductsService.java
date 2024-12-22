package com.sahil.products.service;

import com.sahil.products.dto.ProductDto;

public interface IProductsService {
    void createProduct(ProductDto productDto);
    ProductDto getProduct(Long id, Integer quantity);
}

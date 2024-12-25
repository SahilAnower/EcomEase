package com.sahil.products.service;

import com.sahil.products.dto.ProductDto;

import java.util.List;
import java.util.Map;

public interface IProductsService {
    void createProduct (ProductDto productDto);
    ProductDto getProduct (Long id, Integer quantity);
    List<ProductDto> getAllProducts ();
    List<ProductDto> getProductsByCategory(Long categoryId);
    Map<Long, Double> getProductPriceMap (Map<Long, Integer> productQuantityMap);
}

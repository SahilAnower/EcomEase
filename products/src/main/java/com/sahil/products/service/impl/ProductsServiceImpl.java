package com.sahil.products.service.impl;

import com.sahil.products.dto.ProductDto;
import com.sahil.products.entity.Product;
import com.sahil.products.exception.ProductUnavailableException;
import com.sahil.products.mapper.GenericMapper;
import com.sahil.products.repository.ProductsRepository;
import com.sahil.products.service.IProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements IProductsService {
    private final ProductsRepository productsRepository;
    private final GenericMapper genericMapper;
    @Override
    public void createProduct(ProductDto productDto) {
        Product savedProduct = productsRepository.save(genericMapper.productDtoToProduct(productDto));
    }

    @Override
    public ProductDto getProduct(Long id, Integer quantity) {
        Optional<Product> product = productsRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductUnavailableException("Product not available with id " + id);
        }
        ProductDto productDto = genericMapper.productToProductDto(product.get());
        if (productDto.getAvailableQuantity() < quantity) {
            throw new ProductUnavailableException("Product not available as quantity is less than " + productDto.getAvailableQuantity());
        }
        return productDto;
    }
}

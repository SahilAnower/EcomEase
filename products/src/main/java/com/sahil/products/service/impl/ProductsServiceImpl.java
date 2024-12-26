package com.sahil.products.service.impl;

import com.sahil.products.dto.ProductDto;
import com.sahil.products.dto.ProductQuantityPriceDto;
import com.sahil.products.entity.Product;
import com.sahil.products.exception.ProductUnavailableException;
import com.sahil.products.mapper.GenericMapper;
import com.sahil.products.repository.ProductsRepository;
import com.sahil.products.service.IProductsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements IProductsService {

    private final ProductsRepository productsRepository;
    private final GenericMapper genericMapper;

    private final static Logger logger = LoggerFactory.getLogger(ProductsServiceImpl.class);

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

    @Override
    public List<ProductDto> getAllProducts() {
        return productsRepository.findAll().stream().map(genericMapper::productToProductDto).toList();
    }

    @Override
    public List<ProductDto> getProductsByCategory(Long categoryId) {
        return productsRepository.findByCategoryId(categoryId).stream().map(genericMapper::productToProductDto).toList();
    }

    @Override
    public Map<Long, Double> getProductPriceMap(Map<Long, Integer> productQuantityMap) {
        List<Long> productIds = productQuantityMap.keySet().stream().toList();
        List<ProductQuantityPriceDto> productQuantityPriceDtos = productsRepository.findProductDetailsByIdsNative(productIds);
        Map<Long, Double> productPriceMap = new HashMap<>();
        for (ProductQuantityPriceDto productQuantityPriceDto : productQuantityPriceDtos) {
            Integer requestedQuantity = productQuantityMap.get(productQuantityPriceDto.getId());
            if (productQuantityPriceDto.getAvailableQuantity() >= requestedQuantity) {
                productPriceMap.put(productQuantityPriceDto.getId(), productQuantityPriceDto.getPrice());
            }
        }
        return productPriceMap;
    }

    @Transactional
    @Override
    public void updateInventoryStatus(Map<Long, Integer> productQuantityMap) {
        int totalRowsAffected = 0;
        for (Long productId : productQuantityMap.keySet()) {
            logger.debug("Currently processing inventory update for productId: {} with product order quantity: {}", productId, productQuantityMap.get(productId));
            int rowsAffected = productsRepository.updateProductInventory(productId, productQuantityMap.get(productId));
            if (rowsAffected == 0) {
                throw new IllegalArgumentException(
                        "Insufficient stock or invalid product ID for product ID: " + productId + " with order quantity provided as " + productQuantityMap.get(productId));
            }
            totalRowsAffected += rowsAffected;
        }
        logger.debug("Total rows affected: {}", totalRowsAffected);
    }
}

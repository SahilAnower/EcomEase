package com.sahil.products.mapper;

import com.sahil.products.dto.ProductDto;
import com.sahil.products.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface GenericMapper {
    Product productDtoToProduct(ProductDto productDto);
    ProductDto productToProductDto(Product product);
}

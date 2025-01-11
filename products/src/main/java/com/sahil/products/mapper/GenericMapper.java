package com.sahil.products.mapper;

import com.sahil.products.dto.CategoryDto;
import com.sahil.products.dto.ProductDto;
import com.sahil.products.dto.ReviewDto;
import com.sahil.products.entity.Category;
import com.sahil.products.entity.Product;
import com.sahil.products.entity.Review;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface GenericMapper {

    Product productDtoToProduct(ProductDto productDto);

    @Mappings({
            @Mapping(source = "reviews", target = "reviewDtos", qualifiedByName = "reviewToReviewDto")
    })
    ProductDto productToProductDto(Product product);

    Category categoryDtoToCategory(CategoryDto categoryDto);

    CategoryDto categoryToCategoryDto(Category category);

    @Named("reviewToReviewDto")
    ReviewDto reviewToReviewDto(Review review);

    Review reviewDtoToReview(ReviewDto reviewDto);

    void updateReviewFromReviewDto(ReviewDto reviewDto,@MappingTarget Review review);

}

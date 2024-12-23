package com.sahil.products.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    @NotEmpty(message = "Name of product is required")
    @Size(min = 2, max = 30, message = "The length of the product name should be between 5 and 30")
    private String name;
    @NotEmpty(message = "Description of product is required")
    private String description;
    @NotNull
    private Double price;
    @NotNull
    private Integer availableQuantity;
    private Long categoryId;
}

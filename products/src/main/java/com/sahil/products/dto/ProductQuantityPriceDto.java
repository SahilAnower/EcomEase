package com.sahil.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductQuantityPriceDto {
    private Long id;
    private Integer availableQuantity;
    private Double price;
}

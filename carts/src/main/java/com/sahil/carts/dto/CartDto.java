package com.sahil.carts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long id;
    @NotNull
    @NotEmpty
    private String userId;
    private List<CartItemDto> cartItemDtos;
    private Double totalPrice;
}

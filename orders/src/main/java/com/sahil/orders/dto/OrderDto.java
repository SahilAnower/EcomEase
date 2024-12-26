package com.sahil.orders.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    @NotNull
    private Long cartId;
    private Double totalAmount;
    private Double finalAmount;
    private List<String> couponCodes;
}

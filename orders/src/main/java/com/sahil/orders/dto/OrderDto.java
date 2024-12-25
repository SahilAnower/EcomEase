package com.sahil.orders.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OrderDto {
    private Long id;
    @NotNull
    private Long cartId;
    private Double totalAmount;
    private Double finalAmount;
    private List<String> couponCodes;
}

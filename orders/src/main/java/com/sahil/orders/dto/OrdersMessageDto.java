package com.sahil.orders.dto;

import java.util.Map;

public record OrdersMessageDto(Map<Long, Integer> productDecreaseQuantityMap) {
}

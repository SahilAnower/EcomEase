package com.sahil.orderinventoryprocessor.dto;

import java.util.List;
import java.util.Map;

public record OrdersMessageDto(Map<Long, Integer> productDecreaseQuantityMap) {
}

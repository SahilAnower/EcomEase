package com.sahil.orders.decorator;

import com.sahil.orders.dto.OrderDto;
import com.sahil.orders.entity.Order;

public interface DiscountDecorator {
    OrderDto calculateFinalOrder (OrderDto orderDto, Double value);
}

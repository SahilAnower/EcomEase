package com.sahil.orders.decorator;

import com.sahil.orders.entity.Order;

public interface DiscountDecorator {
    Order calculateFinalOrder (Order order, Double value);
}

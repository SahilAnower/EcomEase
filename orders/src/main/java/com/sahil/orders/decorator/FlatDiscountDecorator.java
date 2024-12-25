package com.sahil.orders.decorator;

import com.sahil.orders.entity.Order;

public class FlatDiscountDecorator implements DiscountDecorator{
    @Override
    public Order calculateFinalOrder(Order order, Double value) {
        Double finalPrice = order.getFinalAmount();
        finalPrice = Math.max(finalPrice - value, 0);
        order.setFinalAmount(finalPrice);
        return null;
    }
}

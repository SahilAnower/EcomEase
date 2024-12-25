package com.sahil.orders.decorator;

import com.sahil.orders.entity.Order;

public class PercentageDiscountDecorator implements DiscountDecorator{
    @Override
    public Order calculateFinalOrder(Order order, Double value) {
        Double finalPrice = order.getFinalAmount();
        finalPrice = finalPrice - (finalPrice * (value / 100));
        order.setFinalAmount(finalPrice);
        return order;
    }
}

package com.sahil.orders.decorator;

import com.sahil.orders.dto.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class PercentageDiscountDecorator implements DiscountDecorator{
    @Override
    public OrderDto calculateFinalOrder(OrderDto orderDto, Double value) {
        Double finalPrice = orderDto.getFinalAmount();
        finalPrice = finalPrice - (finalPrice * (value / 100));
        orderDto.setFinalAmount(finalPrice);
        return orderDto;
    }
}

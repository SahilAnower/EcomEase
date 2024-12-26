package com.sahil.orders.decorator;

import com.sahil.orders.dto.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class FlatDiscountDecorator implements DiscountDecorator{
    @Override
    public OrderDto calculateFinalOrder(OrderDto orderDto, Double value) {
        Double finalPrice = orderDto.getFinalAmount();
        finalPrice = Math.max(finalPrice - value, 0);
        orderDto.setFinalAmount(finalPrice);
        return orderDto;
    }
}

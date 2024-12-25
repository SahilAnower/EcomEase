package com.sahil.orders.service;

import com.sahil.orders.dto.OrderDto;

public interface IOrdersService {
    void placeOrder(OrderDto orderDto);
}

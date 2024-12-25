package com.sahil.orders.service.impl;

import com.sahil.orders.dto.OrderDto;
import com.sahil.orders.mapper.GenericMapper;
import com.sahil.orders.repository.OrdersRepository;
import com.sahil.orders.service.ICouponsService;
import com.sahil.orders.service.IOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class OrdersServiceImpl implements IOrdersService {
    private final OrdersRepository ordersRepository;
    private final GenericMapper genericMapper;
    private final ICouponsService iCouponsService;
    @Override
    public void placeOrder(OrderDto orderDto) {
        // todo: get cartItems, totalAmount from Carts Service
        // todo: set finalAmount after applying coupons
        // todo: sent order inventory update event using rabbitmq
    }
}

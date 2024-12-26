package com.sahil.orders.service.impl;

import com.sahil.orders.decorator.DiscountFactory;
import com.sahil.orders.dto.*;
import com.sahil.orders.entity.Order;
import com.sahil.orders.exception.OrderCannotBeProcessedException;
import com.sahil.orders.mapper.GenericMapper;
import com.sahil.orders.repository.OrdersRepository;
import com.sahil.orders.service.ICouponsService;
import com.sahil.orders.service.IOrdersService;
import com.sahil.orders.service.client.CartsFeignClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RequiredArgsConstructor
@Service
public class OrdersServiceImpl implements IOrdersService {

    private final OrdersRepository ordersRepository;
    private final GenericMapper genericMapper;
    private final ICouponsService iCouponsService;
    private final CartsFeignClient cartsFeignClient;
    private final StreamBridge streamBridge;

    private final static Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);

    @Override
    public void placeOrder(OrderDto orderDto) {
        // get cartItems, totalAmount from Carts Service
        ResponseEntity<CartDto> cartDtoResponseEntity = cartsFeignClient.getCart(orderDto.getCartId());
        if (cartDtoResponseEntity == null || cartDtoResponseEntity.getBody() == null) {
            throw new OrderCannotBeProcessedException("Cart cannot be found with id: " + orderDto.getCartId());
        }
        CartDto cartDto = cartDtoResponseEntity.getBody();
        orderDto.setTotalAmount(cartDto.getTotalPrice());
        orderDto.setFinalAmount(cartDto.getTotalPrice());
        // set finalAmount after applying coupons
        List<CouponDto> couponDtos = iCouponsService.getCoupons(orderDto.getCouponCodes());
        for (CouponDto couponDto : couponDtos) {
            orderDto = Objects.requireNonNull(DiscountFactory.getDiscountDecorator(couponDto.getType()))
                    .calculateFinalOrder(orderDto, couponDto.getValue());
        }
        // sent order inventory update event using rabbitmq
        Order savedOrder = ordersRepository.save(genericMapper.orderDtoToOrder(orderDto));
        sendOrderUpdateCommunication(cartDto.getCartItemDtos());
    }

    private void sendOrderUpdateCommunication(List<CartItemDto> cartItemDtos) {
        Map<Long, Integer> productDecreaseQuantityMap = new HashMap<>();
        for (CartItemDto cartItemDto : cartItemDtos) {
            if (cartItemDto.getIsAvailable()) {
                productDecreaseQuantityMap.put(cartItemDto.getProductId(), cartItemDto.getQuantity());
            }
        }
        var ordersMessageDto = new OrdersMessageDto(productDecreaseQuantityMap);
        logger.info("Sending Communication request for the details: {}", ordersMessageDto);
        var result = streamBridge.send("orderInventoryUpdate-out-0", ordersMessageDto);
        logger.info("Is the Communication request successfully triggered ? : {}", result);
    }
}

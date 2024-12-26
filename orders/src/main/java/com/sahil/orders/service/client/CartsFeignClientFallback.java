package com.sahil.orders.service.client;

import com.sahil.orders.dto.CartDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CartsFeignClientFallback implements CartsFeignClient{
    @Override
    public ResponseEntity<CartDto> getCart(Long cartId) {
        return null;
    }
}

package com.sahil.orders.service.client;

import com.sahil.orders.dto.CartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "carts", fallback = CartsFeignClientFallback.class)
public interface CartsFeignClient {
    @GetMapping("/api/get-cart/{cartId}")
    public ResponseEntity<CartDto> getCart (@PathVariable Long cartId);
}

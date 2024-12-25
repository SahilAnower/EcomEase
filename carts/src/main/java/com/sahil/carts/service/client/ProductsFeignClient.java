package com.sahil.carts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "products", fallback = ProductsFeignClientFallback.class)
public interface ProductsFeignClient {
    @PostMapping(value = "/api/products/get-product-availability")
    public ResponseEntity<Map<Long, Double>> getProductAvailability (@RequestBody Map<Long, Integer> productQuantityMap);
}

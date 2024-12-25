package com.sahil.carts.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProductsFeignClientFallback implements ProductsFeignClient{
    @Override
    public ResponseEntity<Map<Long, Double>> getProductAvailability(Map<Long, Integer> productQuantityMap) {
        return null;
    }
}

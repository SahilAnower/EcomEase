package com.sahil.orderinventoryprocessor.functions;

import com.sahil.orderinventoryprocessor.dto.OrdersMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.function.Function;

@Configuration
public class OrderInventoryProcessorFunctions {
    private static final Logger logger = LoggerFactory.getLogger(OrderInventoryProcessorFunctions.class);

    @Bean
    public Function<OrdersMessageDto, Map<Long, Integer>> processOrder() {
        return ordersMessageDto -> {
            logger.info("Processing order {}", ordersMessageDto);
            return ordersMessageDto.productDecreaseQuantityMap();
        };
    }
}

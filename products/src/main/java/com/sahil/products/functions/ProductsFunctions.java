package com.sahil.products.functions;

import com.sahil.products.service.IProductsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.function.Consumer;

@Configuration
public class ProductsFunctions {

    private static final Logger logger = LoggerFactory.getLogger(ProductsFunctions.class);

    @Bean
    public Consumer<Map<Long, Integer>> getInventoryUpdateCommunication(IProductsService iProductsService) {
        return productQuantityMap -> {
            logger.info("Received Communication response for the products: {}", productQuantityMap.toString());
            iProductsService.updateInventoryStatus(productQuantityMap);
        };
    }

}

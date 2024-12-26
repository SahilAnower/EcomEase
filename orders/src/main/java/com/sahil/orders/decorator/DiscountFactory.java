package com.sahil.orders.decorator;

import org.springframework.stereotype.Component;

@Component
public class DiscountFactory {
    public static DiscountDecorator getDiscountDecorator(String discountType) {
        return switch (discountType) {
            case "FIXED" -> new FlatDiscountDecorator();
            case "PERCENTAGE" -> new PercentageDiscountDecorator();
            default -> null;
        };
    }
}

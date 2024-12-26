package com.sahil.orders.mapper;

import com.sahil.orders.dto.CouponDto;
import com.sahil.orders.dto.OrderDto;
import com.sahil.orders.entity.Coupon;
import com.sahil.orders.entity.Order;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface GenericMapper {
    CouponDto couponToCouponDto(Coupon coupon);
    Coupon couponDtoToCoupon(CouponDto couponDto);
    Order orderDtoToOrder (OrderDto orderDto);
    OrderDto orderToOrderDto (Order order);
}

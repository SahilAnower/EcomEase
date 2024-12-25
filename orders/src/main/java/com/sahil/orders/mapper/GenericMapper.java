package com.sahil.orders.mapper;

import com.sahil.orders.dto.CouponDto;
import com.sahil.orders.entity.Coupon;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface GenericMapper {
    CouponDto couponToCouponDto(Coupon coupon);
    Coupon couponDtoToCoupon(CouponDto couponDto);
}

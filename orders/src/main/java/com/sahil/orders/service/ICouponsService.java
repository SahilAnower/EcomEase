package com.sahil.orders.service;

import com.sahil.orders.dto.CouponDto;

import java.util.List;

public interface ICouponsService {
    void createCoupon(CouponDto couponDto);
    CouponDto getCoupon (String code);
    List<CouponDto> getCoupons (List<String> codes);
}

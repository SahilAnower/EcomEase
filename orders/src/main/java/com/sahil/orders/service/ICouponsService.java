package com.sahil.orders.service;

import com.sahil.orders.dto.CouponDto;

public interface ICouponsService {
    void createCoupon(CouponDto couponDto);
    CouponDto getCoupon (String code);
}

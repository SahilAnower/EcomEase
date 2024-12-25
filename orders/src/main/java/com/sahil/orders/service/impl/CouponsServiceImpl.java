package com.sahil.orders.service.impl;

import com.sahil.orders.dto.CouponDto;
import com.sahil.orders.mapper.GenericMapper;
import com.sahil.orders.repository.CouponsRepository;
import com.sahil.orders.service.ICouponsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CouponsServiceImpl implements ICouponsService {
    private final CouponsRepository couponsRepository;
    private final GenericMapper genericMapper;
    @Override
    public void createCoupon(CouponDto couponDto) {
        couponsRepository.save(genericMapper.couponDtoToCoupon(couponDto));
    }

    @Override
    public CouponDto getCoupon(String code) {
        return couponsRepository.findByCode(code).map(genericMapper::couponToCouponDto).orElse(null);
    }
}

package com.sahil.orders.controller;

import com.sahil.orders.constants.CouponsConstants;
import com.sahil.orders.dto.CouponDto;
import com.sahil.orders.dto.ResponseDto;
import com.sahil.orders.service.ICouponsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/coupons", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class CouponsController {

    private final ICouponsService iCouponsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCoupon(@RequestBody @Valid CouponDto couponDto) {
        iCouponsService.createCoupon(couponDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CouponsConstants.STATUS_201, CouponsConstants.MESSAGE_201));
    }

}

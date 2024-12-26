package com.sahil.orders.controller;


import com.sahil.orders.constants.OrdersConstants;
import com.sahil.orders.dto.OrderDto;
import com.sahil.orders.dto.ResponseDto;
import com.sahil.orders.service.IOrdersService;
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
@RequestMapping(value = "/api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class OrdersController {

    private final IOrdersService iOrdersService;

    @PostMapping("/place")
    public ResponseEntity<ResponseDto> placeOrder(@RequestBody @Valid OrderDto orderDto) {
        iOrdersService.placeOrder(orderDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(OrdersConstants.STATUS_201, OrdersConstants.MESSAGE_201));
    }

}

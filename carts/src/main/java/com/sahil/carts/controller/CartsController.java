package com.sahil.carts.controller;

import com.sahil.carts.constants.CartsConstants;
import com.sahil.carts.dto.CartDto;
import com.sahil.carts.dto.CartItemDto;
import com.sahil.carts.dto.ResponseDto;
import com.sahil.carts.service.ICartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class CartsController {
    private final ICartService iCartService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCart(@Valid @RequestBody CartDto cartDto) {
        iCartService.createCart(cartDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CartsConstants.STATUS_201, CartsConstants.MESSAGE_201));
    }

    @PutMapping("/add-item/{cartId}")
    public ResponseEntity<ResponseDto> addItemToCart (@PathVariable Long cartId, @Valid @RequestBody CartItemDto cartItemDto) {
        iCartService.addItemToCart(cartId, cartItemDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CartsConstants.STATUS_200, CartsConstants.MESSAGE_200));
    }

    @PutMapping("/remove-item/{cartId}/{cartItemId}")
    public ResponseEntity<ResponseDto> removeItemFromCart (@PathVariable Long cartId, @PathVariable Long cartItemId) {
        iCartService.removeItemFromCart(cartId, cartItemId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CartsConstants.STATUS_200, CartsConstants.MESSAGE_200));
    }

    @PutMapping("/update-item/{cartId}/{cartItemId}")
    public ResponseEntity<ResponseDto> updateCartItem (@PathVariable Long cartId, @PathVariable Long cartItemId, @Valid @RequestBody CartItemDto cartItemDto) {
        iCartService.updateCartItem(cartId, cartItemId, cartItemDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CartsConstants.STATUS_200, CartsConstants.MESSAGE_200));
    }

    @GetMapping("/get-cart/{cartId}")
    public ResponseEntity<CartDto> getCart (@PathVariable Long cartId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iCartService.getCart(cartId));
    }
}

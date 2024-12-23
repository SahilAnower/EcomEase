package com.sahil.carts.service;

import com.sahil.carts.dto.CartDto;
import com.sahil.carts.dto.CartItemDto;

public interface ICartService {
    // create cart
    void createCart (CartDto cartDto);
    // add item to cart
    void addItemToCart (Long cartId, CartItemDto cartItemDto);
    // remove item from cart
    void removeItemFromCart (Long cartId, Long cartItemId);
    // update cart item inside of cart
    void updateCartItem (Long cartId, Long cartItemId, CartItemDto cartItemDto);
    // get cart with cartItems
    CartDto getCart (Long cartId);
}

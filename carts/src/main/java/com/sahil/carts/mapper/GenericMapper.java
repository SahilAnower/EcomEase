package com.sahil.carts.mapper;

import com.sahil.carts.dto.CartDto;
import com.sahil.carts.dto.CartItemDto;
import com.sahil.carts.entity.Cart;
import com.sahil.carts.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface GenericMapper {
    @Mapping(source = "cartItemDtos", target = "cartItems")
    Cart cartDtoToCart(CartDto cartDto);
    @Mapping(source = "cartItems", target = "cartItemDtos")
    CartDto cartToCartDto(Cart cart);
    CartItem cartItemDtoToCartItem(CartItemDto cartItemDto);
}

package com.sahil.carts.service.impl;

import com.sahil.carts.dto.CartDto;
import com.sahil.carts.dto.CartItemDto;
import com.sahil.carts.entity.Cart;
import com.sahil.carts.entity.CartItem;
import com.sahil.carts.exception.CartItemNotFoundException;
import com.sahil.carts.exception.CartNotFoundException;
import com.sahil.carts.mapper.GenericMapper;
import com.sahil.carts.repository.CartItemRepository;
import com.sahil.carts.repository.CartRepository;
import com.sahil.carts.service.ICartService;
import com.sahil.carts.service.client.ProductsFeignClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {

    private final GenericMapper genericMapper;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductsFeignClient productsFeignClient;

    @Override
    @Transactional
    public void createCart(CartDto cartDto) {
        Cart cart = genericMapper.cartDtoToCart(cartDto);

        List<CartItem> cartItems = cartDto.getCartItemDtos()
                .stream()
                .map(cartItemDto -> {
                    CartItem cartItem = genericMapper.cartItemDtoToCartItem(cartItemDto);
                    cartItem.setCart(cart); // Set the parent Cart
                    return cartItem;
                })
                .toList();

        cart.setCartItems(cartItems);

        cartRepository.save(cart);
    }

    @Override
    public void addItemToCart(Long cartId, CartItemDto cartItemDto) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with id: " + cartId));

        CartItem cartItem = genericMapper.cartItemDtoToCartItem(cartItemDto);
        cartItem.setCart(cart);
        cart.getCartItems().add(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(Long cartId, Long cartItemId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with id: " + cartId));
        CartItem foundCartItem = null;
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getId().equals(cartItemId)) {
                foundCartItem = cartItem;
                cart.getCartItems().remove(cartItem);
                cartRepository.save(cart);
                break;
            }
        }
        if (foundCartItem == null) {
            throw new CartItemNotFoundException("Cart item not found with id: " + cartItemId + " inside of cart with id: " + cartId);
        }
    }

    @Override
    public void updateCartItem(Long cartId, Long cartItemId, CartItemDto cartItemDto) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with id: " + cartId));
        CartItem foundCartItem = null;
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getId().equals(cartItemId)) {
                foundCartItem = cartItem;
                break;
            }
        }
        if (foundCartItem == null) {
            throw new CartItemNotFoundException("Cart item not found with id: " + cartItemId + " inside of cart with id: " + cartId);
        }
        foundCartItem = genericMapper.cartItemDtoToCartItem(cartItemDto);
        foundCartItem.setCart(cart);
        cartItemRepository.save(foundCartItem);
    }

    @Override
    public CartDto getCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with id: " + cartId));
        double totalPrice = 0.0;
        Map<Long, Integer> productQuantityMap =
                cart.getCartItems().stream().collect(Collectors.toMap(CartItem::getProductId, CartItem::getQuantity));
        ResponseEntity<Map<Long, Double>> productAvailabilityResponseEntity = productsFeignClient.getProductAvailability(productQuantityMap);
        if (productAvailabilityResponseEntity != null && productAvailabilityResponseEntity.getBody() != null) {
            Map<Long, Double> productAvailabilityMap = productAvailabilityResponseEntity.getBody();
            for (CartItem cartItem : cart.getCartItems()) {
                if (productAvailabilityMap.containsKey(cartItem.getProductId())) {
                    totalPrice += cartItem.getQuantity() * productAvailabilityMap.get(cartItem.getProductId());
                    cartItem.setIsAvailable(true);
                }else {
                    cartItem.setIsAvailable(false);
                }
            }
            cart.setTotalPrice(totalPrice);
        }
        return genericMapper.cartToCartDto(cart);
    }
}

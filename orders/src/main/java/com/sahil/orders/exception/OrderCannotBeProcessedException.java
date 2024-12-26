package com.sahil.orders.exception;

public class OrderCannotBeProcessedException extends RuntimeException{
    public OrderCannotBeProcessedException (String message) {
        super(message);
    }
}

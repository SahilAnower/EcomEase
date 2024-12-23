package com.sahil.products.exception;

public class ProductUnavailableException extends RuntimeException {
    public ProductUnavailableException (String message) {
        super(message);
    }
}

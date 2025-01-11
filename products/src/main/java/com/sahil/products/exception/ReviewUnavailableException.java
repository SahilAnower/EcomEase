package com.sahil.products.exception;

public class ReviewUnavailableException extends RuntimeException{
    public ReviewUnavailableException(String message) {
        super(message);
    }
}

package com.dev.diego.backend.exceptions;

public class ProductNotFound extends RuntimeException{
    public ProductNotFound() {
        super("Product not found");
    }
}

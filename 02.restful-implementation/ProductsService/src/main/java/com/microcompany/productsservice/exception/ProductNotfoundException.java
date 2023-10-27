package com.microcompany.productsservice.exception;

public class ProductNotfoundException extends GlobalException {
    protected static final long serialVersionUID = 2L;

    public ProductNotfoundException() {
        super("Product not found");
    }


    public ProductNotfoundException(Long productId) {
        super("Product with id: " + productId + " not found");
    }
}

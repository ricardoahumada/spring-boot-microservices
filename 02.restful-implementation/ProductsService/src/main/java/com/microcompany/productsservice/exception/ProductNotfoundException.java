package com.microcompany.productsservice.exception;


public class ProductNotfoundException extends GlobalException {
    private static final long serialVersionUID = 1L;

    public ProductNotfoundException() {
    }

    public ProductNotfoundException(String message) {
        super(message);
    }
}

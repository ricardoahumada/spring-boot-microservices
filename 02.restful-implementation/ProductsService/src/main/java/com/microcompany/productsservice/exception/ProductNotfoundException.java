package com.microcompany.productsservice.exception;

public class ProductNotfoundException extends GeneralException {
    private static final long serialVersionUID = 1L;

    public ProductNotfoundException(String message) {
        super(message);
    }
}

package com.microcompany.productsservice.exception;

public class GeneralException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public GeneralException(String message) {
        super(message);
    }
}

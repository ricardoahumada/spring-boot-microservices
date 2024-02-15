package com.microcompany.productsservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotfoundException extends GeneralException {
    private static final long serialVersionUID = 1L;

    public ProductNotfoundException(String message) {
        super(message);
    }
}

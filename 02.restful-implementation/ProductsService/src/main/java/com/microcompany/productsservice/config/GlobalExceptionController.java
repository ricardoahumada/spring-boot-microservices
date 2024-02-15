package com.microcompany.productsservice.config;

import com.microcompany.productsservice.exception.GeneralException;
import com.microcompany.productsservice.exception.ProductNotfoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(GeneralException.class)
    ResponseEntity<Object> noSuchElementExceptionHandler(GeneralException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(ProductNotfoundException.class)
    ResponseEntity<Object> noSuchElementExceptionHandler(ProductNotfoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Excepcion de producto");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(exception.getMessage());
    }

}

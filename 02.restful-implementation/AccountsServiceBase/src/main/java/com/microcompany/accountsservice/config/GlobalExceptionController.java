package com.microcompany.accountsservice.config;

import com.microcompany.accountsservice.exception.AccountNotfoundException;
import com.microcompany.accountsservice.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(value = GlobalException.class)
    public ResponseEntity<Object> handleGlobalException(GlobalException exception) {
        return new ResponseEntity<>("GlobalException:"+exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    /*@ExceptionHandler(value = AccountNotfoundException.class)
    public ResponseEntity<Object> exception(AccountNotfoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }*/


}
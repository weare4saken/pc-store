package com.weare4saken.pcstore.exception.handlers;

import com.weare4saken.pcstore.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> notFoundProduct(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}

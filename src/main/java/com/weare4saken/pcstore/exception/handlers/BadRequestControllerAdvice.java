package com.weare4saken.pcstore.exception.handlers;

import com.weare4saken.pcstore.exception.IncorrectArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BadRequestControllerAdvice {

    @ExceptionHandler(IncorrectArgumentException.class)
    public ResponseEntity<?> incorrectArgument() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}

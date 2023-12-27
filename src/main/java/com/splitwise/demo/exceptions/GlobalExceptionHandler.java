package com.splitwise.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends Exception{
    @ExceptionHandler({UserException.class})
    ResponseEntity<String> handleUserException(UserException e){
        String message = e.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler({Exception.class})
    ResponseEntity<String> handleAllException(Exception e){
        String message = e.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }
}

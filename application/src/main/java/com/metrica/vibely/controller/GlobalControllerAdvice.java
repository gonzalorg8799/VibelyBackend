package com.metrica.vibely.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <h1>Global Controller Advice</h1>
 * 
 * @since 2023-11-28
 * @version 1.0
 * @author Anonymus
 */
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Void> notFoundHandler() {
        return ResponseEntity.notFound().build();
    }
    
}
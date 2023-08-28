package com.example.meireles.banker.application.controller.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ExceptionHandlerAdvice {

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleJakartaExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getFieldErrors().
                forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.unprocessableEntity().body(new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, errors));
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataBaseExceptions(DataIntegrityViolationException ex){
        return ResponseEntity.unprocessableEntity().body(new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()));
    }

}

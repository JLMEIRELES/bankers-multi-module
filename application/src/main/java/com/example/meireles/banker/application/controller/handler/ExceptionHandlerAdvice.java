package com.example.meireles.banker.application.controller.handler;

import com.example.meireles.banker.domain.exception.AccountExistsException;
import lombok.extern.slf4j.Slf4j;
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

/**
 * Handle class to intercept all exceptions and create a standard error message
 */
@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ExceptionHandlerAdvice {

    private static final String METHOD = "method = {}";

    /**
     * Creates a standard object of {@link MethodArgumentNotValidException}. This error occurs
     * when a request have an invalid field
     *
     * @param ex the exception
     * @return the @{@link ResponseEntity} content the error
     */
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleJakartaExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getFieldErrors().
                forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));

        log.error(METHOD, ex.getClass().getSimpleName(), ex);
        return ResponseEntity.unprocessableEntity().body(
                new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, errors)
        );
    }

    /**
     * Creates a standard object of {@link DataIntegrityViolationException}. This error occurs
     * when the request could not be processed for the database
     *
     * @param ex the exception
     * @return the @{@link ResponseEntity} content the error
     */
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataBaseExceptions(DataIntegrityViolationException ex){
        log.error(METHOD, ex.getClass().getSimpleName(), ex);
        return ResponseEntity.unprocessableEntity().body(
                new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage())
        );
    }

    /**
     * Creates a standard object of {@link AccountExistsException}. This error occurs
     * when the account to be saved already exists
     *
     * @param ex the exception
     * @return the @{@link ResponseEntity} content the error
     */
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(AccountExistsException.class)
    public ResponseEntity<ErrorResponse> handleAccountExistsException(AccountExistsException ex){
        log.error(METHOD, ex.getClass().getSimpleName(), ex);
        return ResponseEntity.unprocessableEntity().body(
                new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage())
        );
    }

}

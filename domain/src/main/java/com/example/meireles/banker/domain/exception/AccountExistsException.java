package com.example.meireles.banker.domain.exception;

/**
 * Class that represents an exception to already existent accounts
 */
public class AccountExistsException extends RuntimeException{

    public AccountExistsException(String message){
        super(message);
    }

    public AccountExistsException(String message, Throwable cause){
        super(message, cause);
    }

}

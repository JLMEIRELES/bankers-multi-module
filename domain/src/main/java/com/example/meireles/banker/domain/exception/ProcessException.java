package com.example.meireles.banker.domain.exception;

public class ProcessException extends RuntimeException{

    public ProcessException(String message, Throwable cause){
        super(message, cause);
    }

    public ProcessException(String message){
        super(message);
    }

}

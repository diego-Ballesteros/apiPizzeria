package com.platzi.pizza.service.exception;

public class EmailException extends RuntimeException{
    public EmailException(String message) {
        super(message);
    }
}

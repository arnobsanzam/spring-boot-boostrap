package com.example.springbootboostrap.exception;

public class RequestNotPermittedException extends RuntimeException {
    public RequestNotPermittedException(String message) {
        super(message);
    }
}

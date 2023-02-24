package com.example.springbootboostrap.exception;

public class BucketRetrievalFailedException extends RuntimeException {
    public BucketRetrievalFailedException(String message) {
        super(message);
    }
}

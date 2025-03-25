package com.exception;

/**
 * Custom exception for handling resource not found scenarios.
 * Thrown when a requested resource (e.g., blog, comment) is not found in the database.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new exception with the specified detail message.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

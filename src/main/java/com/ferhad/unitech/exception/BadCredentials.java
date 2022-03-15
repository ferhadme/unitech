package com.ferhad.unitech.exception;

public class BadCredentials extends RuntimeException {
    public BadCredentials(String message) {
        super(message);
    }
}

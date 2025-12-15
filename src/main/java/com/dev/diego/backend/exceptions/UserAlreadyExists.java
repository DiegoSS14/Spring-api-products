package com.dev.diego.backend.exceptions;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists() {
        super("User already exists");
    }
}

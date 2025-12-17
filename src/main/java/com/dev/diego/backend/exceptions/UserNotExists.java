package com.dev.diego.backend.exceptions;

public class UserNotExists extends RuntimeException {
    public UserNotExists() {
        super("User not exists");
    }
}
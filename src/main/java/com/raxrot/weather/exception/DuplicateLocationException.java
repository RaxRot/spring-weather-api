package com.raxrot.weather.exception;

public class DuplicateLocationException extends RuntimeException {
    public DuplicateLocationException(String code) {
        super("Location with code '" + code + "' already exists");
    }
}
package com.example.common.exception;

public abstract class CustomException extends RuntimeException {

    private final int code;

    protected CustomException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

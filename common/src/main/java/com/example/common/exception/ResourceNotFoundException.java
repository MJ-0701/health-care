package com.example.common.exception;

import com.example.common.http.ResponseCode;

public class ResourceNotFoundException extends CustomException {
    public ResourceNotFoundException(String message) {
        super(ResponseCode.NOT_FOUND.getCode(), message);
    }
}

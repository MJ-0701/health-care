package com.example.common.exception;

import com.example.common.http.ResponseCode;
import com.example.common.http.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseObject<Object>> handleAllExceptions(Exception ex) {
        logger.error("Unhandled exception occurred", ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseObject.error(
                        ResponseCode.INTERNAL_SERVER_ERROR.getCode(),
                        ResponseCode.INTERNAL_SERVER_ERROR.getMessage()
                ));
    }

    // 유효성 검증 예외 처리 (DTO @Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseObject<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseObject.error(
                        ResponseCode.BAD_REQUEST.getCode(),
                        "Validation failed: " + errors
                ));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseObject<Object>> handleCustomException(CustomException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseObject.error(ex.getCode(), ex.getMessage()));
    }

    // 커스텀 예외 (리소스를 못 찾은 경우)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseObject<Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseObject.error(
                        ResponseCode.NOT_FOUND.getCode(),
                        ex.getMessage() != null ? ex.getMessage() : ResponseCode.NOT_FOUND.getMessage()
                ));
    }


}

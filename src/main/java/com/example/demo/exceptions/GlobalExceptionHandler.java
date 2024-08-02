package com.example.demo.exceptions;

import com.example.demo.util.LoggerUtil;
import com.example.demo.util.constants.ResponseMessages;
import org.springframework.http.HttpStatus;
import com.example.demo.dataTransferObject.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<?>> handleCustomException(CustomException ex, WebRequest request) {
        LoggerUtil.error("Handling CustomException: " + ex.getMessage());
        ApiResponse<?> response = new ApiResponse<>(null, ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(response, ex.getStatus());
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
//        LoggerUtil.error("Handling GlobalException: " + ex.getMessage());
//        return new ResponseEntity<>(ResponseMessages.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGlobalException(Exception ex, WebRequest request) {
        LoggerUtil.error("Handling GlobalException: " + ex.getMessage());
        ApiResponse<?> response = new ApiResponse<>(null, ResponseMessages.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
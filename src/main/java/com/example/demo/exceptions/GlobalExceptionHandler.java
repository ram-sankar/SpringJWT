package com.example.demo.exceptions;

import com.example.demo.util.LoggerUtil;
import com.example.demo.util.constants.ResponseMessages;
import org.springframework.http.HttpStatus;
import com.example.demo.dataTransferObject.ApiResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ApiResponse<?> handleCustomException(CustomException ex, WebRequest request) {
        LoggerUtil.error("Handling CustomException: " + ex.getMessage());
        return new ApiResponse<>(null, ex.getMessage(), ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleGlobalException(Exception ex, WebRequest request) {
        LoggerUtil.error("Handling GlobalException: " + ex.getMessage());
        return new ApiResponse<>(null, ResponseMessages.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
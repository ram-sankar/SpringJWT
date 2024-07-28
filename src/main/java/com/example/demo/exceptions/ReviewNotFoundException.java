package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

public class ReviewNotFoundException extends CustomException {
    private static final long serialVersion = 2;

    public ReviewNotFoundException (String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

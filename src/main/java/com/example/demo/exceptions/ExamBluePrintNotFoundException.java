package com.example.demo.exceptions;
import org.springframework.http.HttpStatus;

public class ExamBluePrintNotFoundException extends CustomException{
    private static final long serialVersion = 1;

    public ExamBluePrintNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

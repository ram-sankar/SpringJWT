package com.example.demo.exceptions;
import org.springframework.http.HttpStatus;

public class ExamBluePrintExsistException extends CustomException{
    private static final long serialVersion = 1;

    public ExamBluePrintExsistException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}

package com.example.demo.exceptions;
import org.springframework.http.HttpStatus;

public class PokemonNotFoundException extends CustomException{
    private static final long serialVersion = 1;

    public PokemonNotFoundException (String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

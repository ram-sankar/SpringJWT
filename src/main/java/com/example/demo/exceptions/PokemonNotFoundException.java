package com.example.demo.exceptions;

public class PokemonNotFoundException extends RuntimeException{
    private static final long serialVersion = 1;

    public PokemonNotFoundException (String message) {
        super(message);
    }
}

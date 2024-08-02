package com.example.demo.controllers;

import com.example.demo.dataTransferObject.ApiResponse;
import com.example.demo.dataTransferObject.PokemonResponse;
import com.example.demo.models.Pokemon;
import com.example.demo.service.PokemonService;
import com.example.demo.util.LoggerUtil;
import com.example.demo.util.constants.ResponseMessages;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {
    PokemonService pokemonService;

    @Autowired
    public PokemonController (PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<PokemonResponse> getPokemons (
            @ParameterObject Pageable pageable
    ) {
        return ResponseEntity.ok(pokemonService.getAllPokemons(pageable.getPageNumber(), pageable.getPageSize()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemonById (@PathVariable("id") int id) {
        Pokemon pokemon = pokemonService.getPokemonById(id);
        return new ResponseEntity<>(pokemon, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pokemon> createPokemon (@RequestBody Pokemon pokemon) {
        return new ResponseEntity<>(pokemonService.createPokemon(pokemon), HttpStatus.CREATED);
    }

    @PutMapping("/{pokemonId}")
    public ResponseEntity<Pokemon> updatePokemon (@RequestBody Pokemon pokemon, @PathVariable("pokemonId") int pokemonId) {
        return ResponseEntity.ok(pokemonService.updatePokemonById(pokemon, pokemonId));
    }

    @DeleteMapping("/{pokemonId}")
    public ResponseEntity<String> deletePokemon (@PathVariable("pokemonId") int pokemonId) {
        pokemonService.deletePokemon(pokemonId);
        return ResponseEntity.ok("Deleted");
    }
}

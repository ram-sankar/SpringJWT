package com.example.demo.controllers;

import com.example.demo.dataTransferObject.PokemonResponse;
import com.example.demo.models.Pokemon;
import com.example.demo.service.PokemonService;
import com.example.demo.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pokemon")
public class PokemonController {
    PokemonService pokemonService;

    @Autowired
    public PokemonController (PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<PokemonResponse> getPokemons (
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return ResponseEntity.ok(pokemonService.getAllPokemons(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public Pokemon getPokemonById (@PathVariable("id") int id) {
        return pokemonService.getPokemonById(id);
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

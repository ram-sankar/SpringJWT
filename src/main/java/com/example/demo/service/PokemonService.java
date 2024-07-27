package com.example.demo.service;

import com.example.demo.dataTransferObject.PokemonResponse;
import com.example.demo.models.Pokemon;

import java.util.List;

public interface PokemonService {
    Pokemon createPokemon(Pokemon pokemonData);
    PokemonResponse getAllPokemons(int pageNo, int pageSize);
    Pokemon getPokemonById(int id);
    Pokemon updatePokemonById(Pokemon pokemon, int id);
    void deletePokemon(int id);
}

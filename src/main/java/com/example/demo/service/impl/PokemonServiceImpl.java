package com.example.demo.service.impl;

import com.example.demo.dataTransferObject.PokemonResponse;
import com.example.demo.exceptions.PokemonNotFoundException;
import com.example.demo.models.Pokemon;
import com.example.demo.repository.PokemonRepository;
import com.example.demo.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonServiceImpl implements PokemonService {
    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceImpl (PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public Pokemon createPokemon(Pokemon pokemonData) {
        Pokemon pokemon =  new Pokemon();
        pokemon.setName(pokemonData.getName());
        pokemon.setType(pokemonData.getType());

        return pokemonRepository.save(pokemon);
    }

    @Override
    public PokemonResponse getAllPokemons (int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);
        List<Pokemon> pokemons1 = pokemons.getContent();

        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setContent(pokemons1);
        pokemonResponse.setPageSize(pageSize);
        pokemonResponse.setPageNo(pageNo);
        pokemonResponse.setTotalElements((int) pokemons.getTotalElements());
        return pokemonResponse;
    }

    @Override
    public Pokemon getPokemonById(int id) {
        return pokemonRepository.findById((long) id).orElseThrow(() -> new PokemonNotFoundException("Poekmon Not found"));
    }

    @Override
    public Pokemon updatePokemonById (Pokemon pokemon, int id) {
        Pokemon pokemonData = pokemonRepository.findById((long) id).orElseThrow(() -> new PokemonNotFoundException("Poekmon Not found"));
        pokemonData.setName(pokemon.getName());
        pokemonData.setType(pokemon.getType());

        return pokemonRepository.save(pokemonData);
    }

    @Override
    public void deletePokemon (int id) {
        pokemonRepository.findById((long) id).orElseThrow(() -> new PokemonNotFoundException("Poekmon Not found"));
        pokemonRepository.deleteById((long) id);
    }
}

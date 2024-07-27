package com.example.demo.repository;

import com.example.demo.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> { //Long is primary key's type
//    Pokemon createPokemon(Pokemon pokemon);
}

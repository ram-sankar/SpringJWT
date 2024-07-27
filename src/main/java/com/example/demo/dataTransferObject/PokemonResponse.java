package com.example.demo.dataTransferObject;

import com.example.demo.models.Pokemon;
import lombok.Data;

import java.util.List;

@Data
public class PokemonResponse {
    private List<Pokemon> content;
    private int pageNo;
    private int pageSize;
    private int totalElements;
}

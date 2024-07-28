package com.example.demo.service;

import com.example.demo.dataTransferObject.ReviewDto;
import com.example.demo.models.Review;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getReviewsByPokemonId(int pokemonId);
    ReviewDto createReview(int pokemonId, Review review);
    ReviewDto getReviewById (int reviewId, int pokemonId);
}

package com.example.demo.controllers;

import com.example.demo.dataTransferObject.ReviewDto;
import com.example.demo.models.Review;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
    ReviewService reviewService;

    @Autowired
    public ReviewController (ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping(path = "/pokemon/{pokemonId}")
    public List<ReviewDto> getReviewsByPokemonId (@PathVariable("pokemonId") int pokemonId) {
        return this.reviewService.getReviewsByPokemonId(pokemonId);
    }

    @GetMapping(path = "/{reviewId}/pokemon/{pokemonId}")
    public ReviewDto getReviewsById (@PathVariable("pokemonId") int pokemonId, @PathVariable("reviewId") int reviewId) {
        return this.reviewService.getReviewById(reviewId, pokemonId);
    }

    @PostMapping(path = "/pokemon/{pokemonId}")
    public ReviewDto createReview (@PathVariable("pokemonId") int pokemonId, @RequestBody Review review) {
        return this.reviewService.createReview(pokemonId, review);
    }
}

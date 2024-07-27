package com.example.demo.service.impl;

import com.example.demo.dataTransferObject.ReviewDto;
import com.example.demo.exceptions.ReviewNotFoundException;
import com.example.demo.models.Pokemon;
import com.example.demo.models.Review;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.service.PokemonService;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    ReviewRepository reviewRepository;
    PokemonService pokemonService;

    @Autowired
    public ReviewServiceImpl (ReviewRepository reviewRepository, PokemonService pokemonService) {
        this.reviewRepository = reviewRepository;
        this.pokemonService = pokemonService;
    }

    @Override
    public List<ReviewDto> getReviewsByPokemonId(int pokemonId) {
        List<Review> reviews = this.reviewRepository.getReviewsByPokemon_Id(pokemonId);
        return reviews.stream().map(content -> mapToDto(content)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto createReview (int pokemonId, Review review) {
        Pokemon pokemon = this.pokemonService.getPokemonById(pokemonId);
        Review review1 = new Review();
        review1.setContent(review.getContent());
        review1.setTitle(review.getTitle());
        review1.setStars(review.getStars());
        review1.setPokemon(pokemon);

        Review newReview = this.reviewRepository.save(review1);
        return mapToDto(newReview);
    }

    @Override
    public ReviewDto getReviewById(int reviewId, int pokemonId) {
        Pokemon pokemon = pokemonService.getPokemonById(pokemonId);
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review Id not available"));

        if(review.getPokemon().getId() != pokemon.getId()) {
            throw new ReviewNotFoundException("This review does not belong to a pokemon");
        }

        return mapToDto(review);
    }

    private ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }
}

package com.example.backendspringcode.service;

import com.example.backendspringcode.dto.ReviewDTO;
import com.example.backendspringcode.dto.ReviewByUserDTO;
import com.example.backendspringcode.model.Place;
import com.example.backendspringcode.model.Review;
import com.example.backendspringcode.model.User;
import com.example.backendspringcode.repository.IPlaceRepository;
import com.example.backendspringcode.repository.IReviewRepository;
import com.example.backendspringcode.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    IReviewRepository iReviewRepository;

    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    IPlaceRepository iPlaceRepository;

    /* ------- CREATE -------- */
    /* Create a list of Reviews for testing purposes */

    public List<Review> addReviews(List<Review> reviewsToAdd) {
        try {
            return iReviewRepository.saveAll(reviewsToAdd); //return statement outside the loop to return after iterating is done

        } catch (Exception e) {
            throw new RuntimeException("Failed to add reviews: " + e.getMessage());
        }
    }
    // for each loop above
    /* declares a variable of type Review - and we call it review (which represents each review we iterate through
     *Reviews is the list we are iterating through
     * In other words, for each review instance of type Review in the reviews list, do this
     */


    public Review addReviewToPlace(String username, Integer placeId, ReviewDTO reviewDTO) {
        // Find the user and place entities
        User user = iUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + username));

        Place place = iPlaceRepository.findById(placeId)
                .orElseThrow(() -> new RuntimeException("Place not found with id: " + placeId));

        // Create a new Review entity from the ReviewDTO
        Review review = new Review();
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        review.setUser(user);
        review.setPlace(place);

        // Save the review entity
        return iReviewRepository.save(review);

    }

    /* ------- RETRIEVE -------- */
    public List<Review> getAllReviews() {
        try {
            return iReviewRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all reviews: " + e.getMessage());
        }
    }



}






package com.example.backendspringcode.controller;

import com.example.backendspringcode.dto.ResponseDTO;
import com.example.backendspringcode.dto.ReviewByUserDTO;
import com.example.backendspringcode.dto.ReviewDTO;
import com.example.backendspringcode.model.Review;
import com.example.backendspringcode.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.backendspringcode.utils.BindingResultProcess.getBindingResponseEntity;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    /* ------- CREATE -------- */


    /*Add a review to a place & review is tied to user*/
    @PostMapping("users/{username}/places/{placeId}")
    public ResponseEntity <?> addReviewToPlace (@PathVariable String username, @PathVariable Integer placeId, @Valid @RequestBody ReviewDTO reviewDTO, BindingResult result) {
        if (result.hasErrors()) {
            return getBindingResponseEntity(result);
        } else {
            Review newReviewCreated = reviewService.addReviewToPlace(username, placeId, reviewDTO);
            return new ResponseEntity<>(newReviewCreated, HttpStatus.CREATED);

        }

    }

    /* ------- RETRIEVE -------- */
    // created to ensure my seeding worked
    @GetMapping
    public ResponseEntity<?> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        if (reviews.isEmpty()) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setError("No reviews found.");
            responseDTO.setHasError(true);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        }
        return ResponseEntity.ok(reviews);
    }


}

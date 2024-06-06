package com.example.backendspringcode.service;

import com.example.backendspringcode.dto.ProfileDTO;
import com.example.backendspringcode.dto.ReviewByUserDTO;
import com.example.backendspringcode.model.Profile;
import com.example.backendspringcode.model.Review;
import com.example.backendspringcode.repository.IProfileRepository;
import com.example.backendspringcode.repository.IReviewRepository;
import com.example.backendspringcode.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {
    @Autowired
    IProfileRepository iProfileRepository;
    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    IReviewRepository iReviewRepository;

    /* ------- RETRIEVE -------- */
//TODO clear out commented code
    public ProfileDTO getUserProfileWithReviews(String username) {
        // get the user's profile
//        Profile profile = iUserRepository.findByUsername(username) .orElseThrow(() -> new RuntimeException("User profile not found for username: " + username)) .getProfile();
        // make sure user exist
        iUserRepository.findByUsername(username) .orElseThrow(() -> new RuntimeException("User profile not found for username: " + username));
        List<Review> reviewsByUser = iReviewRepository.findReviewsByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("No reviews found for this user with username: " + username));

        //get list of reviews
        List<ReviewByUserDTO> listOfReviewsByUser = new ArrayList<>();
        for (Review review : reviewsByUser) {
            ReviewByUserDTO reviewByUserDTO = new ReviewByUserDTO();
            reviewByUserDTO.setPlaceName(review.getPlace().getPlaceName());
            reviewByUserDTO.setPlaceCity(review.getPlace().getCity());
            reviewByUserDTO.setPlaceState(review.getPlace().getState());
            reviewByUserDTO.setPlaceAddress(review.getPlace().getStreetAddress());
            reviewByUserDTO.setTimeReviewCreated(review.getTimeReviewCreated());
            reviewByUserDTO.setRating(review.getRating());
            reviewByUserDTO.setComment(review.getComment());
            listOfReviewsByUser.add(reviewByUserDTO);
        }

        // for user stat

        Integer numberOfPlacesReviewsContributedTo = listOfReviewsByUser.size();


        // create profile DTO
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setNumberPlacesReviewsContributedTo(numberOfPlacesReviewsContributedTo);
        profileDTO.setUserReviews(listOfReviewsByUser);

        return profileDTO;
    }
}

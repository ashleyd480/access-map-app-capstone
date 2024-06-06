package com.example.backendspringcode.config;

import com.example.backendspringcode.model.Place;
import com.example.backendspringcode.model.Review;
import com.example.backendspringcode.model.User;
import com.example.backendspringcode.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewSeeder {

    @Autowired
    ReviewService reviewService;

    @Autowired
    PlaceSeeder placeSeeder;

    @Autowired
    UserSeeder userSeeder;

    /* Randomizer logic */
    /* Random array of comments and rating so that I can seed random data:  */
    /* Math.random is a decimal between 0 and 1
     * array.length is the number of elements in the array
     * (int) casts an integer data type, making the result an integer.
     * result represents the index position of the message to display from the array of possible comments.
     */
    // randomize comment to seed
    private static final String[] randomSeedComments = {
            "comment about low-sensory",
            "comment about hearing-loop",
            "comment about braille-tactile",
            "comment about wheelchair-accessibility",
            "comment about service-animal"
    };

    private static String getRandomComment() {
        int randomIndex = (int) (Math.random() * randomSeedComments.length);
        return randomSeedComments[randomIndex];
    }

    // randomize rating to seed
    private static final Integer[] randomSeedRating = {
            1, 2, 3, 4, 5
    };

    private Integer getRandomRating() {
        int randomIndex = (int) (Math.random() * randomSeedRating.length);
        return randomSeedRating[randomIndex];
    }


    // randomize user from our list of 10 seeded ones
    private User getRandomUser(List<User> users) {
        int randomIndex = (int) (Math.random() * users.size());
        return users.get(randomIndex);
    }

    public void seedReviews(List<Place> seededPlaces, List <User> seededUsers) {
//        List<Place> places = placeSeeder.seedPlaces(List< FeatureTag > tagsToAdd);
//        List<User> users = userSeeder.seedUsers();

        List<Review> reviewsToAdd = new ArrayList<>();
        for (Place place : seededPlaces) {
            for (int i = 0; i < 3; i++) { // Add three reviews for each place
                // we randomly get a user, rating, and comment
                User randomUser = getRandomUser(seededUsers);
                int randomRating = getRandomRating();
                String randomComment = getRandomComment();

                Review review = new Review();
                review.setRating(randomRating);
                review.setComment(randomComment);
                review.setUser(randomUser);
                review.setPlace(place);

                reviewsToAdd.add(review);
            }
        }
        reviewService.addReviews(reviewsToAdd);
    }
}

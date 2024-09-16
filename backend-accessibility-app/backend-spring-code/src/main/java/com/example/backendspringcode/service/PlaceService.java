package com.example.backendspringcode.service;

import com.example.backendspringcode.dto.PlaceSearchResultsDTO;
import com.example.backendspringcode.dto.TagDTO;
import com.example.backendspringcode.model.FeatureTag;
import com.example.backendspringcode.model.Place;
import com.example.backendspringcode.model.Review;
import com.example.backendspringcode.model.User;
import com.example.backendspringcode.repository.IPlaceRepository;
import com.example.backendspringcode.repository.IReviewRepository;
import com.example.backendspringcode.repository.ITagRepository;
import com.example.backendspringcode.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PlaceService {
    private static final Logger log = LoggerFactory.getLogger(PlaceService.class);


    @Autowired
    IPlaceRepository iPlaceRepository;

    @Autowired
    IReviewRepository iReviewRepository;

    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    ITagRepository iTagRepository;


    /* ------- CREATE --------
    /* Create a list of places for seeding test purposes */
    public List<Place> addPlaces(List<Place> placesToAdd) {
        try {
            return iPlaceRepository.saveAll(placesToAdd);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add places: " + e.getMessage());
        }
    }

    /* ------- RETRIEVE -------- */
    /* Get all places to display */
    public List<Place> getAllPlaces() {
        try {
            return iPlaceRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all places: " + e.getMessage());
        }
    }

    public List<PlaceSearchResultsDTO> getPlacesByCity(String username) {


        List<PlaceSearchResultsDTO> placesByUserCityWithDetails = new ArrayList<>();
        User user = iUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        String userCity = user.getCity().toLowerCase(); // Convert city to lowercase
        log.info(userCity + " is user city and username is " + username);
        List<Place> placesInUserCity = iPlaceRepository.findPlacesByCity(userCity).orElseThrow(() -> new RuntimeException("Unable to find places that match city : " + userCity));

        for (Place place : placesInUserCity) {
            List<Review> reviews = iReviewRepository.findReviewsByPlacePlaceId(place.getPlaceId()).orElseThrow(() -> new RuntimeException("Unable to find reviews with place id : " + place.getPlaceId()));
            double averageRating = calculateAverageRating(reviews); // we call the method here and the reviews parameter is passed to the helper method below

            PlaceSearchResultsDTO placeByUserCityWithDetail = new PlaceSearchResultsDTO();
            placeByUserCityWithDetail.setPlaceId(place.getPlaceId());
            placeByUserCityWithDetail.setPlaceName(place.getPlaceName());
            placeByUserCityWithDetail.setStreetAddress(place.getStreetAddress());
            placeByUserCityWithDetail.setCity(place.getCity());
            placeByUserCityWithDetail.setState(place.getState());
            placeByUserCityWithDetail.setZipCode(place.getZipCode());
            placeByUserCityWithDetail.setCategory(place.getCategory());

            // tags at place
            List<TagDTO> tagsAtPlace = iTagRepository.findTagsByPlaceId(place.getPlaceId()).orElseThrow(() -> new RuntimeException("Unable to find tags with place id : " + place.getPlaceId()));

            placeByUserCityWithDetail.setTags(tagsAtPlace);

            // calculate average rating with average method below

            placeByUserCityWithDetail.setAverageRating(averageRating);


            /* add the place DTO that now that has list of tags and average rating, so it's saved with that info and then added back to the list
             */

            placesByUserCityWithDetails.add(placeByUserCityWithDetail);
        }

        return placesByUserCityWithDetails;
    }

    public double calculateAverageRating(List<Review> reviews) {
        if (reviews.isEmpty()) {
            return 0.0;
        }

        double totalRating = 0.0;
        for (Review review : reviews) {
            totalRating = review.getRating() + totalRating;
        }

        return totalRating / reviews.size();
    }


    //get place by place id and display reviews under it
    public Place getPlaceWithReviewsById(Integer placeId) {
        try {

            return iPlaceRepository.findPlaceWithReviewsById(placeId)
                    .orElseThrow(() -> new RuntimeException("Place with reviews not found with  placeId: " + placeId));
        } catch (Exception e) {
            throw new RuntimeException("Failed to get place info for selected place id" + e.getMessage());
        }


    }
    //for dynamic search by either the keyword, city, or both

    public List<PlaceSearchResultsDTO> searchPlaces(String keyword, String city, String sortField, String sortDirection) {

        try {
            if (keyword != null && city != null) {
                List<PlaceSearchResultsDTO> placesResults = iPlaceRepository.findByKeywordAndCity(keyword.toLowerCase(), city.toLowerCase(), sortField, sortDirection)
                        .orElseThrow(() -> new RuntimeException("No places found with the following query of keyword:  " + keyword + " and " + city));

                for (PlaceSearchResultsDTO place : placesResults) {
                    List<TagDTO> tagsAtPlace = iTagRepository.findTagsByPlaceId(place.getPlaceId()).orElseThrow(() -> new RuntimeException("Unable to find tags with place id : " + place.getPlaceId()));
                    place.setTags(tagsAtPlace);
                }

                return placesResults;
            } else if (keyword != null) {
                List<PlaceSearchResultsDTO> placesResults = iPlaceRepository.findByKeyword(keyword.toLowerCase(), sortField, sortDirection)
                        .orElseThrow(() -> new RuntimeException("No places found with this keyword: " + keyword));

                for (PlaceSearchResultsDTO place : placesResults) {
                    List<TagDTO> tagsAtPlace = iTagRepository.findTagsByPlaceId(place.getPlaceId()).orElseThrow(() -> new RuntimeException("Unable to find tags with place id : " + place.getPlaceId()));
                    ;
                    place.setTags(tagsAtPlace);
                }

                return placesResults;
            } else if (city != null) {
                List<PlaceSearchResultsDTO> placesResults = iPlaceRepository.findByCity(city.toLowerCase(), sortField, sortDirection)
                        .orElseThrow(() -> new RuntimeException("No places found at this city: " + city));

                for (PlaceSearchResultsDTO place : placesResults) {
                    List<TagDTO> tagsAtPlace = iTagRepository.findTagsByPlaceId(place.getPlaceId()).orElseThrow(() -> new RuntimeException("Unable to find tags with place id : " + place.getPlaceId()));
                    place.setTags(tagsAtPlace);
                }

                return placesResults;
            } else {
                return Collections.emptyList(); //empty array
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to get place info for selected place id" + e.getMessage());
        }

    }
}


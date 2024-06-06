package com.example.backendspringcode.controller;

import com.example.backendspringcode.dto.PlaceSearchResultsDTO;
import com.example.backendspringcode.dto.ResponseDTO;
import com.example.backendspringcode.model.Place;
import com.example.backendspringcode.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/places")
public class PlaceController {

    @Autowired
    PlaceService placeService;



    /* ------- RETRIEVE -------- */
    @GetMapping
    public ResponseEntity<?> getAllPlaces() {
        List<Place> places = placeService.getAllPlaces();
        if (places.isEmpty()) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setError("No places found.");
            responseDTO.setHasError(true);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        }
        return ResponseEntity.ok(places);
    }

    //get place by user's city (geolocation)

    @GetMapping("users/{username}")
    public ResponseEntity<?> getPlacesByCity(@PathVariable String username) {
        List<PlaceSearchResultsDTO> placesByUserCityWithDetails = placeService.getPlacesByCity(username);
        if (placesByUserCityWithDetails.isEmpty()) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setError("There are no places found for your city.");
            responseDTO.setHasError(true);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        }
        return ResponseEntity.ok(placesByUserCityWithDetails);
    }


    // load a single place by placeId and show reviews underneath

    @GetMapping("/{placeId}")
    public ResponseEntity<?> getPlaceWithReviewsById(@PathVariable Integer placeId) {
        Place placeWithReviews = placeService.getPlaceWithReviewsById(placeId);
        if (placeWithReviews == null) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setError("No place details found.");
            responseDTO.setHasError(true);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        }
        return ResponseEntity.ok(placeWithReviews);
    }

    //for dynamic search by either the keyword, city, or both
    @GetMapping("/search")
    public ResponseEntity<?> searchPlaces(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String city,
            @RequestParam(required = false, defaultValue = "rating") String sortField,
            @RequestParam(required = false, defaultValue = "descending") String sortDirection

    ) {
        List<PlaceSearchResultsDTO> searchResultsPlaces = placeService.searchPlaces(keyword, city, sortField, sortDirection);
        if ((keyword == null || keyword.isEmpty()) && (city == null || city.isEmpty())) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setError("You must enter a place name and/or city.");
            responseDTO.setHasError(true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);

        }
        else if (searchResultsPlaces.isEmpty()) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setError("There are no matches for your search result.");
            responseDTO.setHasError(true);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);

        }

            else {
            return ResponseEntity.ok(searchResultsPlaces);
        }
    }
}

package com.example.backendspringcode.dto;

import com.example.backendspringcode.model.FeatureTag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


// to display places in search result
@Data
@AllArgsConstructor
@NoArgsConstructor

// for rendering geolocated places near user and for rendering places in search results

public class PlaceSearchResultsDTO {
    private Integer placeId;
    private String placeName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String category;
    private List<TagDTO> tags;
    private Double averageRating;

// Constructor with all fields except tags
    /* We need this because in our JPQL when we create a new PlaceSearchResult DTO it requires all these constructors
     *We don't need the tag because the tags are added separately in our service logic
     * */


    public PlaceSearchResultsDTO(Integer placeId, String placeName, String streetAddress, String city, String state, String zipCode, String category, Double averageRating) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.category = category;
        this.averageRating = averageRating;
    }

}






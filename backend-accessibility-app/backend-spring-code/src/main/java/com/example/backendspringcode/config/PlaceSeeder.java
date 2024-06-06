package com.example.backendspringcode.config;

import com.example.backendspringcode.model.FeatureTag;
import com.example.backendspringcode.model.Place;
import com.example.backendspringcode.service.PlaceService;
import com.example.backendspringcode.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaceSeeder {
    @Autowired
    PlaceService placeService;

    @Autowired
    TagService tagService;

    @Autowired
    TagSeeder tagSeeder;

    public List<Place> seedPlaces(List<FeatureTag> randomTags) {
        List<Place> placesToAdd = new ArrayList<>();
        /* We initialize a list of places, and places are added to that list
        * These places are then bulk added with the service method */



        // Miami, FL Places
        placesToAdd.add(createPlace("restaurant1", "restaurant", "1 Main St", "Miami", "FL", "33101", randomTags));
        placesToAdd.add(createPlace("bar1", "bar", "2 Main St", "Miami", "FL", "33102", randomTags));
        placesToAdd.add(createPlace("restaurant2", "restaurant", "3 Main St", "Miami", "FL", "33103", randomTags));
        placesToAdd.add(createPlace("bar2", "bar", "4 Main St", "Miami", "FL", "33104", randomTags));
        placesToAdd.add(createPlace("restaurant3", "restaurant", "5 Main St", "Miami", "FL", "33105", randomTags));
        placesToAdd.add(createPlace("zoo1", "zoo", "6 Main St", "Miami", "FL", "33106", randomTags));
        placesToAdd.add(createPlace("library1", "library", "7 Main St", "Miami", "FL", "33107", randomTags));
        placesToAdd.add(createPlace("museum1", "museum", "8 Main St", "Miami", "FL", "33108", randomTags));
        placesToAdd.add(createPlace("zoo2", "zoo", "9 Main St", "Miami", "FL", "33109", randomTags));
        placesToAdd.add(createPlace("library2", "library", "10 Main St", "Miami", "FL", "33110", randomTags));

        // Austin, TX Places
        placesToAdd.add(createPlace("restaurant1", "restaurant", "1 Main St", "Austin", "TX", "73301", randomTags));
        placesToAdd.add(createPlace("bar1", "bar", "2 Main St", "Austin", "TX", "73302", randomTags));
        placesToAdd.add(createPlace("restaurant2", "restaurant", "3 Main St", "Austin", "TX", "73303", randomTags));
        placesToAdd.add(createPlace("bar2", "bar", "4 Main St", "Austin", "TX", "73304", randomTags));
        placesToAdd.add(createPlace("restaurant3", "restaurant", "5 Main St", "Austin", "TX", "73305", randomTags));
        placesToAdd.add(createPlace("zoo1", "zoo", "6 Main St", "Austin", "TX", "73306", randomTags));
        placesToAdd.add(createPlace("library1", "library", "7 Main St", "Austin", "TX", "73307", randomTags));
        placesToAdd.add(createPlace("museum1", "museum", "8 Main St", "Austin", "TX", "73308", randomTags));
        placesToAdd.add(createPlace("zoo2", "zoo", "9 Main St", "Austin", "TX", "73309", randomTags));
        placesToAdd.add(createPlace("library2", "library", "10 Main St", "Austin", "TX", "73310", randomTags));

        // Los Angeles, CA Places
        placesToAdd.add(createPlace("restaurant1", "restaurant", "1 Main St", "Los Angeles", "CA", "90001", randomTags));
        placesToAdd.add(createPlace("bar1", "bar", "2 Main St", "Los Angeles", "CA", "90002", randomTags));
        placesToAdd.add(createPlace("restaurant2", "restaurant", "3 Main St", "Los Angeles", "CA", "90003", randomTags));
        placesToAdd.add(createPlace("bar2", "bar", "4 Main St", "Los Angeles", "CA", "90004", randomTags));
        placesToAdd.add(createPlace("restaurant3", "restaurant", "5 Main St", "Los Angeles", "CA", "90005", randomTags));
        placesToAdd.add(createPlace("zoo1", "zoo", "6 Main St", "Los Angeles", "CA", "90006", randomTags));
        placesToAdd.add(createPlace("library1", "library", "7 Main St", "Los Angeles", "CA", "90007", randomTags));
        placesToAdd.add(createPlace("museum1", "museum", "8 Main St", "Los Angeles", "CA", "90008", randomTags));
        placesToAdd.add(createPlace("zoo2", "zoo", "9 Main St", "Los Angeles", "CA", "90009", randomTags));
        placesToAdd.add(createPlace("library2", "library", "10 Main St", "Los Angeles", "CA", "90010", randomTags));
        placesToAdd.add(createPlace("restaurant4", "restaurant", "11 Main St", "Los Angeles", "CA", "90011", randomTags));
        placesToAdd.add(createPlace("bar3", "bar", "12 Main St", "Los Angeles", "CA", "90012", randomTags));
        placesToAdd.add(createPlace("restaurant5", "restaurant", "13 Main St", "Los Angeles", "CA", "90013", randomTags));
        placesToAdd.add(createPlace("zoo3", "zoo", "14 Main St", "Los Angeles", "CA", "90014", randomTags));
        placesToAdd.add(createPlace("library3", "library", "15 Main St", "Los Angeles", "CA", "90015", randomTags));
        placesToAdd.add(createPlace("museum2", "museum", "16 Main St", "Los Angeles", "CA", "90016", randomTags));


        // Call the service method to add places to the database
        return placeService.addPlaces(placesToAdd);
    }



    // Helper method to create places


    private Place createPlace(String placeName, String category, String streetAddress, String city, String state, String zipCode, List<FeatureTag> seededTags) {
        Place place = new Place();
        place.setPlaceName(placeName);
        place.setCategory(category);
        place.setStreetAddress(streetAddress);
        place.setCity(city);
        place.setState(state);
        place.setZipCode(zipCode);
        place.setTags(addRandomTags(seededTags)); // Set the tags for the place so  places show up with some tags
        return place;
    }

    // Helper method to select a random tag from a list
    private List<FeatureTag> addRandomTags(List<FeatureTag> seededTags) {
        List<FeatureTag> randomTags = new ArrayList<>();
        // Generate two random indices, so we can pick two random tags from the array.
        int index1 = (int) (Math.random() * seededTags.size());
        int index2 = (int) (Math.random() * seededTags.size());

        // Ensure indices are distinct
        while (index2 == index1) {
            index2 = (int) (Math.random() * seededTags.size());
        }

        // Add tags at the randomly selected indices to the list
        randomTags.add(seededTags.get(index1));
        randomTags.add(seededTags.get(index2));

        return randomTags;
    }

}


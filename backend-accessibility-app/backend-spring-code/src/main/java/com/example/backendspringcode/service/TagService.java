package com.example.backendspringcode.service;

import com.example.backendspringcode.model.FeatureTag;
import com.example.backendspringcode.model.Place;
import com.example.backendspringcode.repository.IPlaceRepository;
import com.example.backendspringcode.repository.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private ITagRepository iTagRepository;
    @Autowired
    private IPlaceRepository iPlaceRepository;

    /* ------- CREATE -------- */
    /* Populate database with our 5 accessibility feature tags
     * The tags are: low-sensory, hearing-loop, braille-tactile, wheelchair-accessible, service-animal
     */
    public List<FeatureTag> addTags(List<FeatureTag> tagsToAdd) {
        try {
            return iTagRepository.saveAll(tagsToAdd);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add tags: " + e.getMessage());
        }
    }

    // check if tag already exists on a place

    public boolean isTagAlreadyOnPlace(Integer placeId, Integer tagId) {
        try {
            Place place = iPlaceRepository.findById(placeId).orElseThrow(() -> new RuntimeException("No places found for this place id: " + placeId));
            FeatureTag tag = iTagRepository.findById(tagId).orElseThrow(() -> new RuntimeException("No places  found for this tag id: " + tagId));
            return place.getTags().contains(tag);
        } catch (Exception e) {
            throw new RuntimeException("Failed to check if tags already on place: " + e.getMessage());
        }
    }


    public FeatureTag addTagToPlace(Integer placeId, Integer tagId) {
        if (!isTagAlreadyOnPlace(placeId, tagId)) {
            // get the place and tag from repository
            Place place = iPlaceRepository.findById(placeId).orElseThrow(() -> new RuntimeException("No places found for this place id: " + placeId));
            FeatureTag tag = iTagRepository.findById(tagId).orElseThrow(() -> new RuntimeException("No places  found for this tag id: " + tagId));


            place.getTags().add(tag);

            iPlaceRepository.save(place);
            return tag;

        } else {
            return null;
        }
    }


    /* ------- RETRIEVE -------- */
// to ensure tag seeding worked
    public List<FeatureTag> getAllTags() {

        try {
            return iTagRepository.findAll();

        } catch (Exception e) {
            throw new RuntimeException("Failed to get all tags: " + e.getMessage());
        }

    }

    //get available tags
    /* 2-dimensional array
It looks through each tag on the all tags list and as it’s on one tag on that all tags list, it goes through the existing tags list and sees if match
 */

    public List<FeatureTag> getAvailableTags(Integer placeId) {
        // Fetch all tags list: This would be the list of 5 current tags that we have in our database. Users can only select tags that system has (similar to Google Maps) (A, B, C, D, E)
        List<FeatureTag> allTagsList = iTagRepository.findAll();

        // Fetch tags associated with the place - ex: it only has 2 tags (A, B)
        Place place = iPlaceRepository.findById(placeId)
                .orElseThrow(() -> new RuntimeException("Place not found for id: " + placeId));
        // these are tags the place already has, we don't want to show these to user
        List<FeatureTag> placeExistingTags = place.getTags();
        // Filter available tags (tags not associated with the place)
        List<FeatureTag> availableTags = new ArrayList<>();
        for (FeatureTag tag : allTagsList) {
            boolean found = false;
            for (FeatureTag existingTag : placeExistingTags) {
                if (tag.getTagId().equals(existingTag.getTagId())) {
                    found = true;
                    break;

                }
            }
            if (!found) {
                availableTags.add(tag);
            }

        }
        return availableTags;
    }

    }


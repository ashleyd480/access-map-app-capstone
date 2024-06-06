package com.example.backendspringcode.controller;

import com.example.backendspringcode.dto.ResponseDTO;
import com.example.backendspringcode.model.FeatureTag;
import com.example.backendspringcode.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tags")

public class TagController {
    @Autowired
    TagService tagService;

    /* ------- CREATE -------- */
    @PostMapping("/{tagId}/places/{placeId}")
    public ResponseEntity<?> addTagToPlace(@PathVariable Integer placeId, @PathVariable Integer tagId) {
        if (tagService.isTagAlreadyOnPlace(placeId, tagId)) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setError("Tag already exists on this place.");
            responseDTO.setHasError(true);
            return ResponseEntity.badRequest().body(responseDTO);
        } else {
          FeatureTag tagAddedToPlace= tagService.addTagToPlace(placeId, tagId);
            return new ResponseEntity<>(tagAddedToPlace, HttpStatus.CREATED);
        }
    }

    /* ------- RETRIEVE -------- */
    // to ensure tag seeding worked
    @GetMapping
    public ResponseEntity<?> getAllTags() {
        List<FeatureTag> tags = tagService.getAllTags();
        if (tags.isEmpty()) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setError("No tags found.");
            responseDTO.setHasError(true);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        }
        return ResponseEntity.ok(tags);
    }


    @GetMapping("/places/{placeId}")
    public ResponseEntity<?> getAvailableTags(@PathVariable Integer placeId) {
        List<FeatureTag> tags = tagService.getAvailableTags(placeId);
        if (tags.isEmpty()) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setError("No tags found.");
            responseDTO.setHasError(true);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        }
        return ResponseEntity.ok(tags);
    }


}


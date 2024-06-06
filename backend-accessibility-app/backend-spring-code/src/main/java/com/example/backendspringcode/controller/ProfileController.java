package com.example.backendspringcode.controller;

import com.example.backendspringcode.dto.ProfileDTO;
import com.example.backendspringcode.dto.ResponseDTO;
import com.example.backendspringcode.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/profiles")
public class ProfileController {

  @Autowired
    ProfileService profileService;

    /* ------- RETRIEVE -------- */

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserProfileWithReviews(@PathVariable String username) {
        ProfileDTO userProfile = profileService.getUserProfileWithReviews(username);

        if (userProfile == null) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setError("User profile with review not found.");
            responseDTO.setHasError(true);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        }

        return ResponseEntity.ok(userProfile);
    }

}

package com.example.backendspringcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewByUserDTO {

    private String placeName;
    private String placeCity;
    private String placeState;
    private String placeAddress;
    private LocalDateTime timeReviewCreated;
    private Integer rating;
    private String comment;
}

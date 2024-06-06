package com.example.backendspringcode.config;

import com.example.backendspringcode.model.FeatureTag;
import com.example.backendspringcode.model.Place;
import com.example.backendspringcode.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/* Notes:
* implementing the CommandLineRunner interface: agrees to use methods defined in that interface
* @ Component marks this Java class as Spring-managed enabling dependency injection, etc
* Spring will recognize it as a bean that should be executed when the Spring application starts up. The run method defined in the CommandLineRunner interface will be invoked, allowing you to define startup tasks or initialization logic within MainDataSeeder.
 */



    @Component
    public class MainDataSeeder implements CommandLineRunner {



        @Autowired
        private UserSeeder userSeeder;

        @Autowired
        private TagSeeder tagSeeder;

        @Autowired
        private PlaceSeeder placeSeeder;

        @Autowired
        private ReviewSeeder reviewSeeder;



        // Resource used: https://www.geeksforgeeks.org/spring-boot-load-initial-data/
        // @Override means Main Data Seeder class provides its own implementation of the CommandLineRunner interface
        /* String... args parameter allows the run method to accept command-line arguments, which can be useful for passing  inputs to the application during startup such as our methods below. */

        /*Note: used variables to represent return to avoid multiple seeding - and w/ a variable, we can then just pass that one-time seeded list to other methods*/
        @Override
        public void run(String... args) throws Exception {
            List<User> seededUsers= userSeeder.seedUsers();
            // Seed users first
            List<FeatureTag> seededTags = tagSeeder.seedTags(); // Then, seed tags first
            List<Place> seededPlaces = placeSeeder.seedPlaces(seededTags); // Pass seeded tags to places
            reviewSeeder.seedReviews(seededPlaces, seededUsers); // Pass seeded places and tags to reviews (because reviews can only exist with a place and places have tags)
        }

    }


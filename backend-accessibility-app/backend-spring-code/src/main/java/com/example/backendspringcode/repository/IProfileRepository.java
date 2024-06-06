package com.example.backendspringcode.repository;

import com.example.backendspringcode.model.Place;
import com.example.backendspringcode.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfileRepository extends JpaRepository<Profile, Integer> {
}

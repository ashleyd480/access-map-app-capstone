package com.example.backendspringcode.repository;

import com.example.backendspringcode.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    /* A boolean way to see if username exists */
    boolean existsByUsername(String username);

    /* A way to get user by username  */

    Optional<User> findByUsername(String username);

    @Transactional
    void deleteUserByUsername(String username);



}


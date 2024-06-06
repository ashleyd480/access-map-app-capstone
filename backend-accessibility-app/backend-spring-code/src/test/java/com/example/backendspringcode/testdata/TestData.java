package com.example.backendspringcode.testdata;

import com.example.backendspringcode.model.User;

import java.util.Arrays;
import java.util.List;

public class TestData {

    public static User testUser1 = makeTestUser1();
    public static User testUser2 = makeTestUser2();
    public static List<User> makeTestUsers() {
        return Arrays.asList(testUser1, testUser2);
    }

    public static User makeTestUser1() {
        testUser1.setUserId(1);
        testUser1.setUsername("user1");
        testUser1.setEmail("user1@example.com");
        testUser1.setPassword("password1");
        testUser1.setFirstName("John");
        testUser1.setLastName("Doe");
        testUser1.setCity("Los Angeles");
        testUser1.setState("CA");

        return testUser1;
    }

    public static User makeTestUser2() {
        User testUser2 = new User();
        testUser2.setUserId(2);
        testUser2.setUsername("user2");
        testUser2.setEmail("user2@example.com");
        testUser2.setPassword("password2");
        testUser2.setCity("Austin");
        testUser2.setState("TX");
        return testUser2;
    }
}

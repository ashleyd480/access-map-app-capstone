package com.example.backendspringcode.service;

import com.example.backendspringcode.model.User;
import com.example.backendspringcode.repository.IUserRepository;
import com.example.backendspringcode.testdata.TestData;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest

public class AuthServiceTest {

    @MockBean
    private IUserRepository mockUserRepository;

    @Autowired
    private AuthService beanAuthService;





    @Test
    public void testExistsByUsername() {
//        User testUser = new User();
//        testUser.setUserId(1);
//        testUser.setUsername("user1");
//        testUser.setEmail("user1@example.com");
//        testUser.setPassword("password1");
//        testUser.setFirstName("John");
//        testUser.setLastName("Doe");
//        testUser.setCity("Los Angeles");
//        testUser.setState("CA");
        // Setup of mock method with "parameters"

//        when(mockUserRepository.existsByUsername("testUser")).thenReturn(true);

        // Test the service method
//        Boolean result = beanAuthService.existsByUsername("testUser");
//        assertEquals(true, result);
    }

    @Test
    public void testIsPasswordValid() {
//        when(mockUserRepository.findByUsername("testUser")).thenReturn(Optional.of(new User("testUser", "password123")));
//        assertTrue(beanAuthService.isPasswordValid("testUser", "password123"));    }

    }
}
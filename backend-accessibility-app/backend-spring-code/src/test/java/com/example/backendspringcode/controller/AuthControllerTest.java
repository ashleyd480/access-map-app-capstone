package com.example.backendspringcode.controller;

import com.example.backendspringcode.dto.LoginDTO;
import com.example.backendspringcode.dto.ResponseDTO;
import com.example.backendspringcode.model.User;
import com.example.backendspringcode.service.AuthService;
import com.example.backendspringcode.service.UserService;
import com.example.backendspringcode.testdata.TestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@Import(AuthController.class)
@WebMvcTest (AuthControllerTest.class)
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    //wire dependency so we can simulate making those endpoint calls

    @MockBean
    private AuthService mockAuthService;


    @MockBean
    private UserService mockUserService;

    // ----------- TEST DATA  -----------
//    private User testUser = TestData.makeTestUser1();
//    private List<User> testUsers = TestData.makeTestUsers();

    @Test
    public void testAuthUserSignUp () throws Exception {
        User testUser = new User();
        testUser.setUserId(1);
        testUser.setUsername("user1");
        testUser.setEmail("user1@example.com");
        testUser.setPassword("password1");
        testUser.setFirstName("John");
        testUser.setLastName("Doe");
        testUser.setCity("Los Angeles");
        testUser.setState("CA");

        // Convert the User object to JSON format
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(testUser);

        // mock the expected return

        when(mockAuthService.existsByUsername(testUser.getUsername())).thenReturn(false);

        when(mockUserService.addUser(testUser)).thenReturn(testUser);

        Mockito.when(mockUserService.addUser(testUser)).thenReturn(testUser);

        // Test the controller method and see if expected output and status is returned; provide "parameters" by URL
        mockMvc.perform(post("/users/signup", testUser)
                        .contentType(MediaType.APPLICATION_JSON) // Set content type
                        .content(userJson)) // Pass JSON content
                .andExpect(status().isCreated())  // Expecting status code 200
                .andExpect(content().json(userJson)); // Response should be the same JSON as request


        verify(mockUserService, times(1)).addUser(testUser);

    }



    @Test
    public void testAuthUserLogin_Success() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("user1");
        loginDTO.setPassword("password1");

        ObjectMapper objectMapper = new ObjectMapper();
        String loginJson = objectMapper.writeValueAsString(loginDTO);

        when(mockAuthService.existsByUsername(loginDTO.getUsername())).thenReturn(true);
        when(mockAuthService.isPasswordValid(loginDTO.getUsername(), loginDTO.getPassword())).thenReturn(true);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Login successful. Welcome, " + loginDTO.getUsername() + "!");
        responseDTO.setHasError(false);
        String responseJson = objectMapper.writeValueAsString(responseDTO);

        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }


}

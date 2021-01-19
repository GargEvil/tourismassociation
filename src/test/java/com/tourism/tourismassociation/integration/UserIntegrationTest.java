package com.tourism.tourismassociation.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourism.tourismassociation.DTO.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("GET /users/1 - SUCCESS")
    void getUserByIdFound() throws Exception {
        //Execute the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 1))

                //Validate the response code
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                //Validate the returned fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.email", is("mahir@gmail.com")))
                .andExpect(jsonPath("$.passwordHash", is("uhuh4848482")));
    }

    @Test
    @DisplayName("GET /users/51 - NOT FOUND")
    void getUserByIdNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 51))

                //Validate that we get a 404 Not Found response
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /users - SUCCESS")
    void createUser() throws Exception {
        UserDTO user = new UserDTO("samir@gmail.com", "sfafmansfl38230023");

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))


                .andExpect(jsonPath("$.id", any(Integer.class)))
                .andExpect(jsonPath("$.email", is("samir@gmail.com")))
                .andExpect(jsonPath("$.passwordHash", is("sfafmansfl38230023")));


    }


    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

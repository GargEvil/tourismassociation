package com.tourism.tourismassociation.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourism.tourismassociation.DTO.RatingDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RatingIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName(" POST /ratings - SUCCESS")
    void createRatingIT() throws Exception {

        RatingDTO ratingDTO = new RatingDTO(5,2L,1L,"sadsaa");

        mockMvc.perform(post("/ratings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ratingDTO)))

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.grade").value(5));


    }

    @Test
    @DisplayName(" GET /ratings - SUCCESS")
    void getAllRatings() throws Exception {
//Has very ambiguous result (2-3) depending on is createRatingIT() run 1. or 2.

        mockMvc.perform(get("/ratings")
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(hasSize(2)));
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

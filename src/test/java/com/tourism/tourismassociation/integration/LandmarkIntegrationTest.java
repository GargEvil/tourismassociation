package com.tourism.tourismassociation.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourism.tourismassociation.DTO.LandmarkDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class LandmarkIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName(" GET /landmarks/1 -- SUCCESS")
    void agetLandmarkByIdIT() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/landmarks/{id}", 2))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))


                .andExpect(jsonPath("$.name", is("Skadarlija")));
    }

    @Test
    @DisplayName("GET /landmarks/{id} -> that is not in DB - NOT FOUND")
    void getLandmarkByIDITNotFound() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/landmarks/{id}", 263))

                .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("POST /landmarks - SUCCESS")
    void createLandmarkIT() throws Exception {

        LandmarkDTO createLandmark = new LandmarkDTO("Spanski Trg", "Setaliste u centru Mostara", 31.3141, 19.376,true);

        mockMvc.perform(post("/landmarks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createLandmark)))

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.name").value("Spanski Trg"));

    }

    @Test
    @DisplayName(" POST /landmarks - Exception (same name)")
    void createLandmarkITException() throws Exception {

        LandmarkDTO createLandmark = new LandmarkDTO("Bascarsija", "Setaliste u centru Mostara", 31.3141, 19.376,true);

        assertThatThrownBy(()->
            mockMvc.perform(post("/landmarks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(createLandmark)))
        ).hasCause(new RuntimeException("Landmark with this name already exists"));

    }



    @Test
    @DisplayName("PUT /landmarks/1 - SUCCESS")
    void updateLandmarkIT() throws Exception {
        LandmarkDTO createLandmark = new LandmarkDTO("Spanski Trg", "Setaliste u centru Mostara", 31.3141, 19.376,true);

        mockMvc.perform(put("/landmarks/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createLandmark)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))


                .andExpect(jsonPath("$.name").value("Spanski Trg"));

    }

   @Test
   @DisplayName("DELETE /landmarks/1 - SUCCESS")
   void deleteLandmarkIT() throws Exception {

       mockMvc.perform(delete("/landmarks/{id}", 1))

               .andExpect(status().isOk());

   }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


package com.tourism.tourismassociation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourism.tourismassociation.model.Landmark;
import com.tourism.tourismassociation.service.LandmarkService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ExtendWith(SpringExtension.class)
public class LandmarkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LandmarkService landmarkService;

    @Test
    @DisplayName("GET /landmarks - Found")
    void getAllLandmarks() throws Exception {
        //arrange
        List<Landmark> landmarkList = new ArrayList<>();
        landmarkList.add(new Landmark(1,"Bascarsija",  43.8598, 18.4313, true));
        landmarkList.add(new Landmark(2,"Skadarlija",  48.8598, 15.4313, true));
        when(landmarkService.findAll()).thenReturn(landmarkList);

        //act
        mockMvc.perform(MockMvcRequestBuilders.get("/landmarks")
                .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(jsonPath("$", hasSize(2))).andDo(print());

    }


    @Test
    @DisplayName("GET /landmarks/{id} - Found")
    void successfullyFoundLandmarkById() throws Exception {
        //arrange
        Landmark mockLandmark = new Landmark(1,"Bascarsija",43.8598,18.4313, false);
        doReturn(Optional.of(mockLandmark)).when(landmarkService).findById(1L);

        //act
        mockMvc.perform(MockMvcRequestBuilders.get("/landmarks/{id}",1))
                //assert
                //Validating response type and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))


                //Validate the returned fields
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.name",is("Bascarsija")))
                .andExpect(jsonPath("$.geoLatitude",is(43.8598)))
                .andExpect(jsonPath("$.geoLongitude",is(18.4313)))
                .andExpect(jsonPath("$.active", is(false)));

    }

    @Test
    @DisplayName("POST /landmarks - CREATED")
    void successfullyCreateLandmark() throws Exception {
        //arrange - Eventually should create LandmarkDTO and map it to LandmarkEntity
        Landmark mockLandmark = new Landmark(1,"Bascarsija",43.8598,18.4313, false);
        when(landmarkService.save(any(Landmark.class))).thenReturn(mockLandmark);
        ObjectMapper objectMapper = new ObjectMapper();
        String createLandmarkJSON = objectMapper.writeValueAsString(mockLandmark);

        //act
        ResultActions result = mockMvc.perform(post("/landmarks")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(createLandmarkJSON));

        //assert
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.name",is("Bascarsija")))
                .andExpect(jsonPath("$.geoLatitude",is(43.8598)))
                .andExpect(jsonPath("$.geoLongitude",is(18.4313)))
                .andExpect(jsonPath("$.active",is(false)));


    }

    @Test
    @DisplayName("PUT /landmarks/{id} - UPDATED")
    void landmarkPutSuccess() throws Exception {
        //arrange
        Landmark mockLandmark = new Landmark(1,"Bascarsija",43.8598,18.4313, false);
        Landmark putLandmark = new Landmark("Skadarlija", 55.231, 15.531, true);
        doReturn(Optional.of(mockLandmark)).when(landmarkService).findById(1L);
        doReturn(true).when(landmarkService).update(any());

        //act
        mockMvc.perform(put("/landmarks/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putLandmark)))

                //assert
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Skadarlija")))
                .andExpect(jsonPath("$.geoLatitude", is(55.231)))
                .andExpect(jsonPath("$.geoLongitude", is(15.531)))
                .andExpect(jsonPath("$.active", is(true)));




    }

    @Test
    @DisplayName("DELETE /landmarks/{id} - SUCCESS")
    void landmarkDeleteSuccess() throws Exception {
        //arrange
        Landmark landmark = new Landmark(1,"Bascarsija",43.8598,18.4313, false);
        doReturn(Optional.of(landmark)).when(landmarkService).findById(1L);
        doReturn(true).when(landmarkService).delete(1L);

        //act
        mockMvc.perform(delete("/landmarks/{id}",1))
                //assert
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

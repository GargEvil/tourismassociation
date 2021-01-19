package com.tourism.tourismassociation.controller;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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


}

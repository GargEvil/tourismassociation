package com.tourism.tourismassociation.controller;

import com.tourism.tourismassociation.service.RatingService;
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

import static org.mockito.Mockito.when;

@WebMvcTest
@ExtendWith(SpringExtension.class)
public class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingService ratingService;

    @Test
    @DisplayName(" Get /ratings - SUCCESS")
    void getAllRatings() throws Exception {
        //arrange
        List<RatingDTO> ratingList = new ArrayList<>();
        ratingList.add(new RatingDTO(1, 1, "Prelijep osjecaj je biti na ovoj lokaciji u centru Sarajeva."));
        ratingList.add(new RatingDTO(1, null, "Na ljeto sam posjetio Sarajevo, i nezaobilaznu Bascarsiju, uzivao sam svaki trenutak."));
        when(ratingService.findAll()).thenReturn(ratingList);

        //act
        mockMvc.perform(MockMvcRequestBuilders.get("/ratings")
                .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(jsonPath("$", hasSize(2))).andDo(print());


    }
}

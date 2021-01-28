package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.DTO.RatingDTO;
import com.tourism.tourismassociation.repository.RatingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class RatingServiceTest {

    @MockBean
    private RatingRepository ratingRepository;

    @Autowired
    private RatingService ratingService;


    @Test
    @DisplayName("Test findAllRatings - SUCCESS")
    void getAllRatingsSuccessTest(){
        //arrange
        RatingDTO mockRating1 = new RatingDTO(4,1L,1L,"safas");
        RatingDTO mockRating2 = new RatingDTO(2,1L,null,"safas2");
        doReturn(Arrays.asList(mockRating1,mockRating2)).when(ratingRepository).findAll();

        //act
        List<RatingDTO> ratingList = ratingService.findAll();

        //assert
        assertEquals(2, ratingList.size(), "There should be 2 rating in list");
    }

}

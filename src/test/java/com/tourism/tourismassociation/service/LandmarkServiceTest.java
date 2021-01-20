package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.model.Landmark;
import com.tourism.tourismassociation.repository.LandmarkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
public class LandmarkServiceTest {

    @MockBean
    private LandmarkRepository landmarkRepository;

    @Autowired
    private LandmarkService landmarkService;

    @Test
    @DisplayName("Test findAllLandmarks - SUCCESS")
    void getAllLandmarks(){
        //arrange
        Landmark mockLandmark1 = new Landmark(1,"Bascarsija",43.8598,18.4313, false);
        Landmark mockLandmark2 = new Landmark(2,"Skadarlija",43.8598,18.4313, true);

        //act
        List<Landmark> landmarkList = landmarkService.findAll();

        //assert
        Assertions.assertEquals(2,landmarkList.size(), "There should be 2 landmarks in list");
    }
}

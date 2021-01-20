package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.model.Landmark;
import com.tourism.tourismassociation.repository.LandmarkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class LandmarkServiceTest {

    @MockBean
    private LandmarkRepository landmarkRepository;

    @Autowired
    private LandmarkService landmarkService;

    @Test
    @DisplayName("Test findAllLandmarks - SUCCESS")
    void getAllLandmarksSuccess(){
        //arrange
        Landmark mockLandmark1 = new Landmark(1,"Bascarsija",43.8598,18.4313, false);
        Landmark mockLandmark2 = new Landmark(2,"Skadarlija",43.8598,18.4313, true);
        doReturn(Arrays.asList(mockLandmark1,mockLandmark2)).when(landmarkRepository).findAll();

        //act
        List<Landmark> landmarkList = landmarkService.findAll();

        //assert
        Assertions.assertEquals(2,landmarkList.size(), "There should be 2 landmarks in list");

    }

    @Test
    @DisplayName(" Test findAllLandmarks - FAILURE")
    void getAllLandmarksFailure(){
        //arrange
        Landmark mockLandmark1 = new Landmark(1,"Bascarsija",43.8598,18.4313, false);
        Landmark mockLandmark2 = new Landmark(2,"Skadarlija",43.8598,18.4313, true);
        Landmark mockLandmark3 = new Landmark(3,"Skadarlija",43.8598,18.4313, true);
        doReturn(Arrays.asList(mockLandmark1,mockLandmark2,mockLandmark3)).when(landmarkRepository).findAll();

        //act
        List<Landmark> landmarkList = landmarkService.findAll();

        //assert
        Assertions.assertNotEquals(2, landmarkList.size(), "It should show an error");
    }

    @Test
    @DisplayName("Test findById - SUCCESS")
    void getLandmarkById(){
        //arrange
        Landmark mockLandmark = new Landmark(1,"Bascarsija",43.8598,18.4313, false);
        doReturn(Optional.of(mockLandmark)).when(landmarkRepository).findById(1L);

        //act
        Optional<Landmark> returnedLandmark = landmarkService.findById(1L);

        //assert
        Assertions.assertTrue(returnedLandmark.isPresent());
        Assertions.assertSame(returnedLandmark.get(), mockLandmark, "Landmarks should be the same");
    }

    @Test
    @DisplayName("Test findById - NOT FOUND")
    void getLandmarkByIdNotFound(){
        //arrange
        Landmark mockLandmark = new Landmark(1,"Bascarsija",43.8598,18.4313, false);
        doReturn(Optional.empty()).when(landmarkRepository).findById(1L);


        //act
        Optional<Landmark> returnedLandmark = landmarkService.findById(1L);

        //assert
        Assertions.assertFalse(returnedLandmark.isPresent(),"Landmark was found, when it shouldn't be");
    }

    @Test
    @DisplayName("Test saveLandmark - SUCCESS")
    void saveLandmarkTest(){
       //arrange
        Landmark mockLandmark = new Landmark(1,"Bascarsija",43.8598,18.4313, false);
        doReturn(mockLandmark).when(landmarkRepository).save(any());

        //act
        Landmark returnedLandmark = landmarkService.save(mockLandmark);

        //assert
        Assertions.assertNotNull(returnedLandmark, "Saved landmark should not be null");
        Assertions.assertEquals(1,returnedLandmark.getId());
        Assertions.assertEquals("Bascarsija", returnedLandmark.getName());
        Assertions.assertEquals(43.8598, returnedLandmark.getGeoLatitude());
        Assertions.assertEquals(18.4313, returnedLandmark.getGeoLongitude());
        Assertions.assertEquals(false, returnedLandmark.isActive());

    }

}

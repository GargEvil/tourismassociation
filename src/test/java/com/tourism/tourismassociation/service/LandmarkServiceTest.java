package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.DTO.LandmarkDTO;
import com.tourism.tourismassociation.DTO.MunicipalityDTO;
import com.tourism.tourismassociation.DTO.SignificanceDTO;
import com.tourism.tourismassociation.model.Landmark;
import com.tourism.tourismassociation.model.Municipality;
import com.tourism.tourismassociation.model.Significance;
import com.tourism.tourismassociation.repository.LandmarkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class LandmarkServiceTest {

    @MockBean
    private LandmarkRepository landmarkRepository;

    @Autowired
    private LandmarkService landmarkService;

    Landmark landmarkEntity;

    @BeforeEach
    void setUp(){

       landmarkEntity = new Landmark();

        landmarkEntity.setId(1L);
        landmarkEntity.setName("Bascarsija");
        landmarkEntity.setDescription("Stari dio grada Sarajeva");
        landmarkEntity.setGeoLatitude(34.121);
        landmarkEntity.setGeoLongitude(19.212);
        landmarkEntity.setMunicipality(getMunicipalityEntity());
        landmarkEntity.setSignificance(getSignificanceEntity());

    }

    @Test
    @DisplayName("Test findAllLandmarks - SUCCESS")
    void getAllLandmarksSuccessTest(){
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
    void getAllLandmarksFailureTest(){
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
    void getLandmarkByIdSuccessTest(){
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
    void saveLandmarkSuccessTest(){
       //arrange
        Landmark mockLandmark = new Landmark(1,"Bascarsija",43.8598,18.4313, false);
        doReturn(mockLandmark).when(landmarkRepository).save(any());

        //act
        Landmark returnedLandmark = landmarkService.save(mockLandmark);

        //assert
        assertNotNull(returnedLandmark, "Saved landmark should not be null");
        Assertions.assertEquals(1,returnedLandmark.getId());
        Assertions.assertEquals("Bascarsija", returnedLandmark.getName());
        Assertions.assertEquals(43.8598, returnedLandmark.getGeoLatitude());
        Assertions.assertEquals(18.4313, returnedLandmark.getGeoLongitude());
        Assertions.assertEquals(false, returnedLandmark.isActive());

    }

    // Doing test in different way using setUp()
    @Test
    @DisplayName("Test createLandmark - SUCCESS")
    void createLandmarkSuccessTest(){

        when(landmarkRepository.save(any(Landmark.class))).thenReturn(landmarkEntity);

        LandmarkDTO landmarkDTO = new LandmarkDTO();
        landmarkDTO.setName("Bascarsija");
        landmarkDTO.setDescription("Stari dio grada Sarajeva");
        landmarkDTO.setGeoLatitude(34.121);
        landmarkDTO.setGeoLongitude(19.212);
        landmarkDTO.setMunicipality(getMunicipalityDTO());
        landmarkDTO.setSignificance(getSignificanceDTO());


        LandmarkDTO storeLandmark = landmarkService.createLandmark(landmarkDTO);
        assertNotNull(storeLandmark);
        assertEquals(landmarkEntity.getMunicipality().getId(), storeLandmark.getMunicipality().getId());
        assertEquals(landmarkEntity.getSignificance().getId(), storeLandmark.getSignificance().getId());
        assertEquals(landmarkEntity.getName(), storeLandmark.getName());
        assertEquals(landmarkEntity.getGeoLatitude(), storeLandmark.getGeoLatitude());
        assertEquals(landmarkEntity.getGeoLongitude(), storeLandmark.getGeoLongitude());
        assertEquals(landmarkEntity.isActive(), storeLandmark.isActive());
        verify(landmarkRepository, times(1)).save(any(Landmark.class));


    }

    private MunicipalityDTO getMunicipalityDTO(){

        MunicipalityDTO municipalityDTO = new MunicipalityDTO();
        municipalityDTO.setId(1);

        return municipalityDTO;
    }

    private Municipality getMunicipalityEntity(){

        MunicipalityDTO municipalityDTO = getMunicipalityDTO();

        Type municipalityEntity = new TypeToken<Municipality>() {}.getType();

        return new ModelMapper().map(municipalityDTO, municipalityEntity);
    }

    private SignificanceDTO getSignificanceDTO(){

        SignificanceDTO significanceDTO = new SignificanceDTO();
        significanceDTO.setId(1);

        return significanceDTO;
    }

    private Significance getSignificanceEntity(){

        SignificanceDTO significanceDTO = getSignificanceDTO();

        Type significanceEntity = new TypeToken<Significance>() {}.getType();

        return new ModelMapper().map(significanceDTO, significanceEntity);
    }
}

package com.tourism.tourismassociation.repository;


import com.tourism.tourismassociation.model.Landmark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class LandmarkRepositoryTest {

    @Autowired
    LandmarkRepository landmarkRepository;

    @Test
    @DisplayName("Test getAllFromDB - SUCCESS")
    void getAllFromDb(){
        List<Landmark> landmarkList = landmarkRepository.findAll();

        assertEquals(2,landmarkList.size(), "We should have 2 landmarks in db, as submitted in data.sql");
    }

    @Test
    @DisplayName("Test getLandmarkById - SUCCESS")
    void getLandmarkFromDbById()
    {
        Optional<Landmark> landmark = landmarkRepository.findById(1L);

        assertTrue(landmark.isPresent());

        //Validating some of fields
        Landmark l = landmark.get();
        assertEquals(1L, l.getId());
        assertEquals("Bascarsija", l.getName());
        assertEquals(43.8598, l.getGeoLatitude());
    }

    @Test
    @DisplayName("Test createAndSaveToDB - SUCCESS")
    void createLandmarkAndSaveToDB(){

       Landmark landmark = new Landmark("Bascarsija", 55.231, 32.232,true);
       Landmark savedLandmark = landmarkRepository.save(landmark);

       Optional<Landmark> loadedLandmark = landmarkRepository.findById(savedLandmark.getId());
       assertTrue(loadedLandmark.isPresent());
       assertEquals(loadedLandmark.get().getId(), savedLandmark.getId());
       assertEquals(loadedLandmark.get().getName(), savedLandmark.getName());

    }

    @Test
    @DisplayName("Test deleteUserFronDB - SUCCESS")
    void deleteLandmarkFromDB(){

        Landmark landmarkToDelete = landmarkRepository.findById(1L).orElse(null);

        assertNotNull(landmarkToDelete);

        landmarkRepository.delete(landmarkToDelete);

        assertEquals(1, landmarkRepository.findAll().size());
    }


    @Test
    @DisplayName("Test getFromDBByLandmarkName ")
    void getLandmarkByName(){

        String name = "Bascarsija";

        Landmark landmarkFromDB = landmarkRepository.findByName(name);

        assertNotNull(landmarkFromDB);

        assertEquals(name, landmarkFromDB.getName());

    }

}

package com.tourism.tourismassociation.repository;


import com.tourism.tourismassociation.model.Rating;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith({SpringExtension.class})
public class RatingRepositoryTest {

    @Autowired
    RatingRepository ratingRepository;

    @Test
    @DisplayName("Test getAllRatingFromDb - SUCCESS")
    void getAllRatingsFromDb(){
        List<Rating> ratingList = ratingRepository.findAll();

        assertEquals(2, ratingList.size(), "We should have 2 ratings in DB");
    }

    @Test
    @DisplayName("Test InsertRatingInDB - SUCCESS")
    void saveRatingToDb()
    {
        //arrange
        Long userId = 1L;
        Long landmarkId = 1L;
        String comment = "Prelijepo.";
        int grade = 5;


        //act
        ratingRepository.insert(comment, grade, landmarkId, userId);
        List<Rating> ratingList = ratingRepository.findAll();

        //assert
        assertEquals(3, ratingList.size(), " Now it should be 3 ratings in db");
    }
}

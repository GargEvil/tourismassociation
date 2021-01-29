package com.tourism.tourismassociation.repository;

import com.tourism.tourismassociation.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Transactional
    @Modifying
    @Query(value="INSERT INTO rating (comment, grade, landmark_id, user_id) VALUES " +
            "(:comment, :grade, :landmarkId, :userId)", nativeQuery=true)
    void insert(
            @Param("comment") String comment,
            @Param("grade") Integer grade,
            @Param("landmarkId") Long landmarkId,
            @Param("userId") Long userId);

    ArrayList<Rating> findByLandmarkId(Long id);
}

package com.tourism.tourismassociation.repository;

import com.tourism.tourismassociation.model.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LandmarkRepository extends JpaRepository<Landmark, Long> {

    Landmark findByName(String name);

    @Query(value="select * from Landmark l where l.name LIKE %:keyword%",nativeQuery=true)
    List<Landmark> findLandmarksByKeyword(@Param("keyword") String keyword);
}

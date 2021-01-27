package com.tourism.tourismassociation.repository;

import com.tourism.tourismassociation.model.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandmarkRepository extends JpaRepository<Landmark, Long> {

    Landmark findByName(String name);
}

package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.model.Landmark;

import java.util.List;
import java.util.Optional;

public interface LandmarkService {

    List<Landmark> findAll();

    Optional<Landmark> findById(Long id);

    Landmark save(Landmark landmark);

    boolean update(Landmark landmark);

    boolean delete(Long id);
}

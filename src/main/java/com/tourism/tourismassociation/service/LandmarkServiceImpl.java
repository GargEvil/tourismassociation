package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.model.Landmark;
import com.tourism.tourismassociation.repository.LandmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LandmarkServiceImpl implements LandmarkService {

    @Autowired
    private LandmarkRepository landmarkRepository;

    public List<Landmark> findAll() {
        return landmarkRepository.findAll();
    }

    public Optional<Landmark> findById(Long id) {
        return Optional.empty();
    }

    public Landmark save(Landmark landmark) {
        return null;
    }

    public boolean update(Landmark landmark) {
        return false;
    }

    public boolean delete(Long id) {
        return false;
    }
}

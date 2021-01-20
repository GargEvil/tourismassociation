package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.model.Landmark;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LandmarkServiceImpl implements LandmarkService {


    public List<Landmark> findAll() {
        return new ArrayList<>();
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

package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.model.Landmark;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LandmarkServiceImpl implements LandmarkService {


    public List<Landmark> findAll() {
        return new ArrayList<>();
    }
}

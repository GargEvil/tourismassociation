package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.DTO.RatingDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {

    List<RatingDTO> findAll();
}

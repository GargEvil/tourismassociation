package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.DTO.RatingDTO;

import java.util.List;


public interface RatingService {

    /**
     * Returns all ratings in database
     *
     * @return  ALL Ratings
     */
    List<RatingDTO> findAll();
}

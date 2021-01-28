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

    /**
     * Creates rating and saves it to the db
     *
     * @param ratingDTO     Rating to be created
     * @return              Created rating
     */
    RatingDTO createRating(RatingDTO ratingDTO);
}

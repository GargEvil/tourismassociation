package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.DTO.LandmarkDTO;
import com.tourism.tourismassociation.model.Landmark;

import java.util.List;
import java.util.Optional;

public interface LandmarkService {

    /**
     *Returns all landmarks in database

     * @return All landmarks in the database
     **/
    List<Landmark> findAll();

    /**
     * Returns the landmark with specified id
     *
     * @param  id       ID of the landmark to retrieve
     * @return          Requested landmark if found
     */
    Optional<Landmark> findById(Long id);

    /**
     * Saves the specified user
     *
     * @param landmark   The landmark to save to the database
     * @return       The saved landmark.
     */
    Landmark saveLandmark(Landmark landmark);

    /**
     * Updates the landmark with specified id
     *
     * @param landmark      body/properties to be changed
     * @param id            ID of the Landmark
     * @return              DTO of the updated landmark
     */
    LandmarkDTO updateLandmark(LandmarkDTO landmark, Long id);

    /**
     * Deletes landmark with specified id
     *
     * @param id        ID of landmark to be deleted
     * @return          true/false
     */
    boolean deleteLandmark(Long id);

    /**
     * Creates landmark
     *
     * @param landmarkDTO       DTO of landmark to be created
     * @return                  DTO of created landmark
     */
    LandmarkDTO createLandmark(LandmarkDTO landmarkDTO);
}

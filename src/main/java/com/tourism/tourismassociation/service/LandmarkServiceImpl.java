package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.DTO.LandmarkDTO;
import com.tourism.tourismassociation.exceptions.LandmarkServiceException;
import com.tourism.tourismassociation.model.Landmark;
import com.tourism.tourismassociation.repository.LandmarkRepository;
import com.tourism.tourismassociation.ui.response.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LandmarkServiceImpl implements LandmarkService {

    @Autowired(required = false)
    private LandmarkRepository landmarkRepository;

    public List<Landmark> findAll(String keyword) {
        if(keyword == null) {
            return landmarkRepository.findAll();
        }
        else{
            return landmarkRepository.findLandmarksByKeyword(keyword);
        }
    }

    public Optional<Landmark> findById(Long id) {
        return landmarkRepository.findById(id);
    }

    public Landmark saveLandmark(Landmark landmark) {
        return landmarkRepository.save(landmark);
    }

    @Override
    public LandmarkDTO updateLandmark(LandmarkDTO landmark, Long id) {

        Landmark landmarkEntity = landmarkRepository.findById(id)
                .orElseThrow(()-> new LandmarkServiceException(ErrorMessages.LANDMARK_NO_RECORD_FOUND.getErrorMessage()));

        //findById does not return id of landmark, instead i set it manually
        ModelMapper modelMapper = new ModelMapper();
        landmarkEntity = modelMapper.map(landmark, Landmark.class);
        landmarkEntity.setId(id);

        landmarkRepository.save(landmarkEntity);

        return modelMapper.map(landmarkEntity, LandmarkDTO.class);

    }


    public boolean deleteLandmark(Long id) {

        Landmark landmarkEntity = landmarkRepository.findById(id)
                .orElseThrow(()-> new LandmarkServiceException(ErrorMessages.LANDMARK_NO_RECORD_FOUND.getErrorMessage()));


        landmarkRepository.delete(landmarkEntity);

        return true;

    }

    @Override
    public LandmarkDTO createLandmark(LandmarkDTO landmarkDTO) {

        if(landmarkDTO.getName().isEmpty() || landmarkDTO.getGeoLatitude() == 0 || landmarkDTO.getGeoLongitude() ==0)
            throw new LandmarkServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        if(landmarkRepository.findByName(landmarkDTO.getName()) != null)
            throw new LandmarkServiceException(ErrorMessages.LANDMARK_RECORD_ALREADY_EXISTS.getErrorMessage());

        ModelMapper modelMapper = new ModelMapper();
        Landmark landmarkEntity = modelMapper.map(landmarkDTO, Landmark.class);

        landmarkRepository.save(landmarkEntity);

        return modelMapper.map(landmarkEntity, LandmarkDTO.class);
    }

    @Override
    public void updateAvgRating(float avgRating, Long landmarkId) {
        landmarkRepository.update(avgRating, landmarkId);
    }


}

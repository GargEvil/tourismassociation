package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.DTO.LandmarkDTO;
import com.tourism.tourismassociation.model.Landmark;
import com.tourism.tourismassociation.repository.LandmarkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LandmarkServiceImpl implements LandmarkService {

    @Autowired(required = false)
    private LandmarkRepository landmarkRepository;

    public List<Landmark> findAll() {
        return landmarkRepository.findAll();
    }

    public Optional<Landmark> findById(Long id) {
        return landmarkRepository.findById(id);
    }

    public Landmark save(Landmark landmark) {
        return landmarkRepository.save(landmark);
    }

    public boolean update(Landmark landmark) {
        return false;
    }

    public boolean delete(Long id) {
        return false;
    }

    @Override
    public LandmarkDTO createLandmark(LandmarkDTO landmarkDTO) {


        ModelMapper modelMapper = new ModelMapper();
        Landmark landmarkEntity = modelMapper.map(landmarkDTO, Landmark.class);

        Landmark storedLandmark = landmarkRepository.save(landmarkEntity);


        return modelMapper.map(landmarkEntity, LandmarkDTO.class);
    }


}

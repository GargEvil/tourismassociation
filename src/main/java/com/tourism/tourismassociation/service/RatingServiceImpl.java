package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.DTO.RatingDTO;
import com.tourism.tourismassociation.repository.RatingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    RatingRepository ratingRepository;


    @Override
    public List<RatingDTO> findAll() {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(ratingRepository.findAll(), List.class);
    }

    @Override
    public RatingDTO createRating(RatingDTO ratingDTO) {
        return null;
    }
}

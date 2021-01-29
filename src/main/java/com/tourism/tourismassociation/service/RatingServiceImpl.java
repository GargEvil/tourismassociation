package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.DTO.RatingDTO;
import com.tourism.tourismassociation.repository.RatingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired(required = false)
    RatingRepository ratingRepository;


    @Override
    public List<RatingDTO> findAll() {
        ModelMapper modelMapper = new ModelMapper();

        //it's returning List<Rating> instead of List<RatingDTO>
        //remember to refactor
        return modelMapper.map(ratingRepository.findAll(), List.class);
    }

    @Override
    public RatingDTO createRating(RatingDTO ratingDTO) {

        ratingRepository.insert(
                ratingDTO.getComment(),
                ratingDTO.getGrade(),
                ratingDTO.getLandmarkId(),
                ratingDTO.getUserId()
        );

        //if there was any problem sql would show an error
        return ratingDTO;
    }
}

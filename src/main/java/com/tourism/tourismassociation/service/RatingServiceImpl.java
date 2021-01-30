package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.DTO.RatingDTO;
import com.tourism.tourismassociation.exceptions.RatingServiceException;
import com.tourism.tourismassociation.model.Rating;
import com.tourism.tourismassociation.repository.RatingRepository;
import com.tourism.tourismassociation.ui.response.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired(required = false)
    RatingRepository ratingRepository;

    @Autowired
    LandmarkService landmarkService;


    @Override
    public List<RatingDTO> findAll() {
        ModelMapper modelMapper = new ModelMapper();

        //it's returning List<Rating> instead of List<RatingDTO>
        //remember to refactor
        return modelMapper.map(ratingRepository.findAll(), List.class);
    }

    @Override
    public RatingDTO createRating(RatingDTO ratingDTO) {

        if(ratingDTO.getLandmarkId() == 0 )
            throw new RatingServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        ratingRepository.insert(
                ratingDTO.getComment(),
                ratingDTO.getGrade(),
                ratingDTO.getLandmarkId(),
                ratingDTO.getUserId()
        );

        // Calculating avgRating for landmark
        List<Rating> ratingsForLandmark = ratingRepository.findByLandmarkId(ratingDTO.getLandmarkId());
        int sum = 0;
        for(int i = 0; i< ratingsForLandmark.size(); i++){
            sum += ratingsForLandmark.get(i).getGrade();
        }

        float avgRating = (float) sum/ratingsForLandmark.size();
        landmarkService.updateAvgRating(avgRating, ratingDTO.getLandmarkId());

        //if there was any problem sql would throw an error
        return ratingDTO;
    }
}

package com.tourism.tourismassociation.controller;

import com.tourism.tourismassociation.DTO.RatingDTO;
import com.tourism.tourismassociation.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<RatingDTO>> getAllRatings(){

        return new ResponseEntity<>(ratingService.findAll(), HttpStatus.OK);
    }
}

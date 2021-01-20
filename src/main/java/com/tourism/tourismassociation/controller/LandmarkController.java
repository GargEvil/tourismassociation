package com.tourism.tourismassociation.controller;

import com.tourism.tourismassociation.model.Landmark;
import com.tourism.tourismassociation.service.LandmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/landmarks")
public class LandmarkController {

    @Autowired
    private LandmarkService landmarkService;

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<Landmark>> getAllLandmarks() {

        return new ResponseEntity<>(landmarkService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    ResponseEntity<Landmark> getLandmarkById(@PathVariable("id") Long id){

        return landmarkService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound()
                .build());
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Landmark> createLandmark(@RequestBody Landmark landmark)
    {
        return new ResponseEntity<>(landmarkService.save(landmark), HttpStatus.CREATED);
    }

}

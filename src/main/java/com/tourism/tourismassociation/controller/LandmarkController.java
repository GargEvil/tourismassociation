package com.tourism.tourismassociation.controller;

import com.tourism.tourismassociation.DTO.LandmarkDTO;
import com.tourism.tourismassociation.model.Landmark;
import com.tourism.tourismassociation.service.LandmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/landmarks")
public class LandmarkController {

    @Autowired
    private LandmarkService landmarkService;

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<Landmark>> getAllLandmarks(@RequestParam(required = false) String keyword) {

        //When I create UI for application this should be LandmarkEntity -> LandmarkResponseModel

        return new ResponseEntity<>(landmarkService.findAll(keyword), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    ResponseEntity<Landmark> getLandmarkById(@PathVariable("id") Long id){

        return landmarkService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound()
                .build());
    }

    @RequestMapping(value = "/significance/{significanceId}", method = RequestMethod.GET)
    ResponseEntity<List<Landmark>> getLandmarkBySignificanceId(@PathVariable("significanceId") Long significanceId){

        return new ResponseEntity<>( landmarkService.findBySignificanceId(significanceId), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<LandmarkDTO> createLandmark(@Valid @RequestBody LandmarkDTO landmarkDTO)
    {

        return new ResponseEntity<>(landmarkService.createLandmark(landmarkDTO), HttpStatus.CREATED);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    ResponseEntity<?> updateLandmark(@Valid @RequestBody LandmarkDTO landmark,
                                    @PathVariable Long id){

       return new ResponseEntity<>(landmarkService.updateLandmark(landmark, id), HttpStatus.OK);

    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteLandmark(@PathVariable Long id){

        Optional<Landmark> existingLandmark = landmarkService.findById(id);

        return existingLandmark.map(l ->{
            if(landmarkService.deleteLandmark(l.getId())){
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }).orElse(ResponseEntity.notFound().build());
    }

}

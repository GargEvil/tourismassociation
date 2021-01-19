package com.tourism.tourismassociation.controller;

import com.tourism.tourismassociation.DTO.UserDTO;
import com.tourism.tourismassociation.model.User;
import com.tourism.tourismassociation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<User>> getAllUsers()
    {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value="{id}",method = RequestMethod.GET)
    ResponseEntity<User> getUser(@PathVariable("id") Long id)
    {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound()
                        .build());
    }



    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<User> CreateUser(@RequestBody UserDTO user)
    {
        User userEntity = user.convertToUserEntity();

        return new ResponseEntity<>(userService.save(userEntity), HttpStatus.CREATED);
    }

}

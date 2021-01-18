package com.tourism.tourismassociation.controllers;

import com.tourism.tourismassociation.DTO.UserDTO;
import com.tourism.tourismassociation.model.User;
import com.tourism.tourismassociation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    ResponseEntity<List<User>> getAllUsers()
    {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    ResponseEntity<User> getUser(@PathVariable("id") Long id)
    {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound()
                        .build());
    }



    @PostMapping("/users")
    ResponseEntity<User> CreateUser(@RequestBody UserDTO user)
    {
        User userEntity = user.convertToUserEntity();

        return new ResponseEntity<>(userService.save(userEntity), HttpStatus.CREATED);
    }

}

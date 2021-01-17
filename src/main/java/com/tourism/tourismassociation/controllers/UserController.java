package com.tourism.tourismassociation.controllers;

import com.tourism.tourismassociation.model.User;
import com.tourism.tourismassociation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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
    ResponseEntity<User> getUser(@PathVariable Long id)
    {

        //Should find a way to use AutoMapper in java
        return (ResponseEntity<User>) userService.findById(id)
                .map(user -> {
                    try {
                        return ResponseEntity
                                .ok()
                                .eTag(Long.toString(user.getId()))
                                .location(new URI("/users/" + user.getId()))
                                .body(user);
                    } catch (URISyntaxException e ) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());



    }

    @PostMapping("/users")
    ResponseEntity<User> CreateUser(@RequestBody User user)
    {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

}

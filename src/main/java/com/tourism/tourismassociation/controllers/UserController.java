package com.tourism.tourismassociation.controllers;

import com.tourism.tourismassociation.model.User;
import com.tourism.tourismassociation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping("/users")
    ResponseEntity<User> CreateUser(@RequestBody User user)
    {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

}

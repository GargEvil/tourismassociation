package com.tourism.tourismassociation.controller;

import com.tourism.tourismassociation.DTO.UserDTO;
import com.tourism.tourismassociation.exceptions.UserServiceException;
import com.tourism.tourismassociation.model.User;
import com.tourism.tourismassociation.service.UserService;
import com.tourism.tourismassociation.ui.request.UserRequestModel;
import com.tourism.tourismassociation.ui.response.ErrorMessages;
import com.tourism.tourismassociation.ui.response.UserResponseModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    ResponseEntity<User> getUserById(@PathVariable("id") Long id)
    {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound()
                        .build());
    }



    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<UserResponseModel> createUser(@Valid @RequestBody UserRequestModel userRequest)  {

        if(userRequest.getEmail().isEmpty() || userRequest.getPassword().isEmpty())
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        UserResponseModel returnedUser = new UserResponseModel();

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userRequest, userDTO);

        UserDTO createdUser = userService.createUser(userDTO);
        BeanUtils.copyProperties(createdUser, returnedUser);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getUserId())
                .toUri();

        return ResponseEntity.created(location).body(returnedUser);

    }

}

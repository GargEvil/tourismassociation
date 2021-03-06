package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.DTO.UserDTO;
import com.tourism.tourismassociation.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    /**
    *Returns all users in database

     * @return All users in the database
     **/
     List<User> findAll();

    /**
     * Saves the specified user
     *
     * @param user   The user to save to the database
     * @return       The saved user.
     */
     User saveUser(User user);

    /**
     * Create a User
     *
     * @param user      userDTO that should be mapped to userEntity
     * @return          created user
     */
     UserDTO createUser(UserDTO user);
    /**
     * Returns the user with specified id
     * @param  id       ID of the user to retrieve
     * @return          Requested User if found
     */
     Optional<User> findById(Long id);

    /**
     * Returns the UserDTO with specified email
     *
     * @param email     EMAIL of the user to retrieve
     * @return          Requested User
     */
     UserDTO getUser(String email);
}

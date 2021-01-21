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
     User save(User user);


     UserDTO createUser(UserDTO user);
    /**
     * Returns the user with specified id
     * @param  id       ID of the user to retrieve
     * @return          Requested User if found
     */
     Optional<User> findById(Long id);
}

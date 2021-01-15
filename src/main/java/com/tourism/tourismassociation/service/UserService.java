package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.model.User;

import java.util.List;

public interface UserService {

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
}

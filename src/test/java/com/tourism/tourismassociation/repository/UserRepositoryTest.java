package com.tourism.tourismassociation.repository;

import com.tourism.tourismassociation.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Test getAllFromDB - SUCCESS")
     void getAllFromDB(){
        List<User> users = userRepository.findAll();

        assertEquals(2, users.size(), "We should have 2 users in database.");
    }

    @Test
    @DisplayName("Test getUserByIdFromDB - SUCCESS")
     void getUserByIdFromDB(){
        //Find user with id 1
        Optional<User> user = userRepository.findById(1L);

        //Validate that we found it
        Assertions.assertTrue(user.isPresent());

        // Validate user values
        User u = user.get();
        Assertions.assertEquals(1L, u.getId());
        Assertions.assertEquals("mahir@gmail.com", u.getEmail());
        Assertions.assertEquals("uhuh4848482", u.getPasswordHash());

    }
    @Test
    @DisplayName("Test createUserAndSaveToDB - SUCCESS")
     void createUserAndSaveToDB(){
    // Creating User
        User user = new User("dzenita@hotmail.com", "vav35avs23s", "325232");
        User savedUser = userRepository.save(user);

    //Validating that user is saved
        assertEquals("dzenita@hotmail.com", savedUser.getEmail());

    //Validating that we can get it out of the database
        Optional<User> loadedUser = userRepository.findById(savedUser.getId());
        assertTrue(loadedUser.isPresent(), "Could not reload user from db");
        assertEquals(loadedUser.get().getId(), savedUser.getId(), "Id of saved user and loaded user should be the same");
        assertEquals("dzenita@hotmail.com", loadedUser.get().getEmail(), "Email of loaded user should be the same");
        assertEquals("vav35avs23s", loadedUser.get().getPasswordHash(), "PasswordHash of loaded user should be the same");


    }

}

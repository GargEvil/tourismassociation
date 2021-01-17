package com.tourism.tourismassociation.repository;

import com.tourism.tourismassociation.model.User;
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
    public void getALlFromDB(){
        List<User> users = userRepository.findAll();

        assertEquals(2, users.size(), "We should have 2 users in database.");
    }

    @Test
    public void createUserAndSaveToDB(){
    // Creating User
        User user = new User("dzenita@hotmail.com", "vav35avs23s");
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

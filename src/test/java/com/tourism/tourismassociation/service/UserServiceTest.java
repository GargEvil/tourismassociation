package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.model.User;
import com.tourism.tourismassociation.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Test findAllUsers - Success")
    void getAllUsers(){
        //arrange
        User mockUser = new User("mahir@gmail.com","huhuh452323");
        User mockUser2 = new User("adem@gmail.com","huhuh452323");
        doReturn(Arrays.asList(mockUser,mockUser2)).when(userRepository).findAll();

        //act
        List<User> userList = userService.findAll();

        //assert
        Assertions.assertEquals(2, userList.size(), "FindAll should return 2 users");

    }

    @Test
    @DisplayName("Test findById - Success")
    void getById(){
        //arrange
        User mockUser = new User (3L,"muahmed@gmail.com", "rjfifjs483937", "325232");
        doReturn(Optional.of(mockUser)).when(userRepository).findById(3L);

        //act
        Optional<User> returnedUser = userService.findById(3L);

        //assert
        Assertions.assertTrue(returnedUser.isPresent(), "User was not found");
        Assertions.assertSame(returnedUser.get(), mockUser, "Users should be the same");
    }
    @Test
    @DisplayName("Test SaveUser - Success")
    void saveUser(){
        //arrange
        User mockUser = new User(1,"mahir@gmail.com", "jgjgjg3592","25232");
        doReturn(mockUser).when(userRepository).save(any());

        //act
        User returnedUser = userService.save(mockUser);

        //assert
        Assertions.assertNotNull(returnedUser, "Saved user should not be null");
        Assertions.assertEquals(1, returnedUser.getId());
        Assertions.assertEquals("mahir@gmail.com", returnedUser.getEmail());
        Assertions.assertEquals("jgjgjg3592", returnedUser.getPasswordHash());

    }

}
